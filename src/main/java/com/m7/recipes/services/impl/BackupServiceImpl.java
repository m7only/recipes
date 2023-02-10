package com.m7.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m7.recipes.services.BackupService;
import com.m7.recipes.services.FIleService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BackupServiceImpl implements BackupService {

    FIleService fIleService;

    public BackupServiceImpl(FIleService fIleService) {
        this.fIleService = fIleService;
    }

    @Override
    public void SaveMap(Map<?, ?> mapToSave, String fileName) {
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
    public Optional<Map<?, ?>> LoadMap(String fileName) {
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
