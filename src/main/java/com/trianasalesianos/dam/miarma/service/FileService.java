package com.trianasalesianos.dam.miarma.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteFile(String filename);

    void deleteAll();

    String resizeImage(MultipartFile file);


}
