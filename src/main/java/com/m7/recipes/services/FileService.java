package com.m7.recipes.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;

public interface FileService {

    Path save(String data, Path path);

    Optional<String> read(Path path);

    void clean(Path path);

    boolean download(MultipartFile file, Path path);
}
