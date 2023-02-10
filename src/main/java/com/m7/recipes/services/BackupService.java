package com.m7.recipes.services;

import java.util.Map;
import java.util.Optional;

public interface BackupService {
    void SaveMap(Map<?, ?> mapToSave, String fileName);

    Optional<Map<?, ?>> LoadMap(String fileName);
}
