package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.config.StorageProperties;
import com.trianasalesianos.dam.miarma.exception.StorageException;
import com.trianasalesianos.dam.miarma.service.FileService;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("No se pudo inicializar la localización", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteFile(String filename) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public String resizeImage(MultipartFile file) {

        BufferedImage scaled = Scalr.resize(original, 512);

        OutputStream out = Files.newOutputStream(Paths.get("triana-thumb.jpeg"));

        ImageIO.write(scaled, "jpg", out);
        return null;
    }
}
