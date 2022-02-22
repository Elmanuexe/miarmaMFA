package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.config.StorageProperties;
import com.trianasalesianos.dam.miarma.errores.excepciones.EmptyFileException;
import com.trianasalesianos.dam.miarma.resource.MediaTypeUrlResource;
import com.trianasalesianos.dam.miarma.service.FileService;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @PostConstruct
    @Override
    public void init() throws IOException {
        Files.createDirectories(rootLocation);
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if(file.isEmpty()) throw new EmptyFileException(EmptyFileException.class);
        String extension="jpg";

        String nombreArchivo=generarNombre(file)+"."+extension;

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(nombreArchivo),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        return nombreArchivo;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public String reescalarAndGuardar(MultipartFile file,int size) throws IOException {
        String extension="jpg";
        String nombreArchivo=generarNombre(file);
        nombreArchivo+="."+extension;

        BufferedImage bufferedImage= ImageIO.read(file.getInputStream());
        BufferedImage escalado = Scalr.resize(bufferedImage, size);

        OutputStream out=Files.newOutputStream(Paths.get("uploads/"+nombreArchivo));
        ImageIO.write(escalado,extension,out);
        return nombreArchivo;
    }

    @Override
    public Resource cargarRecurso(String filename) throws MalformedURLException, FileNotFoundException {
        Path file = load(filename);
        MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        else   throw new FileNotFoundException(
                "No se pudo leer el archivo: " + filename);
    }


    private String generarNombre (MultipartFile file){
        String extension="jpg";

        Double nuevoNombreDigito = Math.random();
        String nuevoNombre = nuevoNombreDigito.toString().substring(2,10);
        String nombreFinal= nuevoNombre+"."+extension;

        while(Files.exists(rootLocation.resolve(nombreFinal))){

            nuevoNombreDigito = Math.random();
            nuevoNombre = nuevoNombreDigito.toString().substring(2,10);
            nombreFinal= nuevoNombre+"."+extension;

        }
        return nuevoNombre;
    }

    public String getUri (String fileName){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resource/")
                .path(fileName)
                .toUriString();
    }
}
