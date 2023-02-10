package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.BackupService;
import com.m7.recipes.services.IngredientService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Validated
public class IngredientServiceImpl implements IngredientService {
    private static Integer counter = 0;
    private Map<Integer, Ingredient> ingredientStorage = new HashMap<>();
    @Value("${ingredient.backup.file.name}")
    private String fileName;
    private final BackupService backupService;

    public IngredientServiceImpl(BackupService backupService) {
        this.backupService = backupService;
    }

    @PostConstruct
    private void backupLoad() {
        ingredientStorage = backupService.loadBackup(ingredientStorage, fileName).orElse(ingredientStorage);
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientStorage.put(counter++, ingredient);
        backupService.saveBackup(ingredientStorage, fileName);
        return ingredient;
    }

    @Override
    public Optional<Ingredient> getIngredientById(Integer id) {
        Validate.notNull(id);
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return Optional.ofNullable(ingredientStorage.get(id));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientStorage.values().stream().toList();
    }

    @Override
    public Optional<Ingredient> editIngredient(Integer id, Ingredient ingredient) {
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        ingredientStorage.put(id, ingredient);
        backupService.saveBackup(ingredientStorage, fileName);
        return Optional.ofNullable(ingredient);
    }

    @Override
    public Optional<Ingredient> deleteIngredient(Integer id) {
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Ingredient ingredient = ingredientStorage.remove(id);
        backupService.saveBackup(ingredientStorage, fileName);
        return Optional.ofNullable(ingredient);
    }
}
