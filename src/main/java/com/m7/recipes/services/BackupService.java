package com.m7.recipes.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public interface BackupService {
    <T> Path saveBackup(T mapToSave, String fileName);

    <K, V> Optional<Map<K, V>> loadBackup(Class<K> kClass,
                                          Class<V> vClass,
                                          String fileName);

    <K, V> Optional<Map<K, V>> uploadBackupFile(Class<K> kClass,
                                                Class<V> vClass,
                                                MultipartFile file,
                                                String fileName);
}
