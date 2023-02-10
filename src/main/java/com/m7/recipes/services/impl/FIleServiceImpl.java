package com.m7.recipes.services.impl;

import com.m7.recipes.services.FIleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class FIleServiceImpl implements FIleService {
    @Value("${path.to.backup.folder}")
    String backupFolder;

    @Override
    public void save(String data, String fileName) {
        Path path = Path.of(backupFolder, fileName);
        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<String> read(String fileName) {
        Path path = Path.of(backupFolder, fileName);
        try {
            return Optional.ofNullable(Files.readString(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void clean(String fileName) {
        Path path = Path.of(backupFolder, fileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
