package com.m7.recipes.services;

import java.util.Optional;

public interface BackupService {
    <T> void saveBackup(T mapToSave, String fileName);

    <T> Optional<T> loadBackup(T map, String fileName);
}
