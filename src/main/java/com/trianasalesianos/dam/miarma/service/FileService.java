package com.trianasalesianos.dam.miarma.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

    void init() throws IOException;

    String saveFile(MultipartFile file) throws IOException;

    Resource cargarRecurso(String filename) throws MalformedURLException, FileNotFoundException;

    Path load(String filename);

    String reescalarAndGuardar(MultipartFile file,int size) throws IOException;

    String getUri(String nameFile);
}
