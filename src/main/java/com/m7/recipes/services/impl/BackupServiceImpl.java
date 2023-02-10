package com.m7.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m7.recipes.services.BackupService;
import com.m7.recipes.services.FileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackupServiceImpl implements BackupService {

    FileService fIleService;

    public BackupServiceImpl(FileService fIleService) {
        this.fIleService = fIleService;
    }

    @Override
    public <T> void saveBackup(T mapToSave, String fileName) {
        try {
            fIleService.save(
                    new ObjectMapper().writeValueAsString(mapToSave),
                    fileName
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> Optional<T> loadBackup(T type, String fileName) {
        try {
            return Optional.ofNullable(
                    new ObjectMapper().readValue(
                            fIleService.read(fileName).orElse(""),
                            new TypeReference<>() {
                            }
                    )
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
