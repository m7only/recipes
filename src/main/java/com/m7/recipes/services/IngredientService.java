package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    void addIngredient(List<Ingredient> ingredients);

    Optional<Ingredient> getIngredientById(Integer id);

    List<Ingredient> getAllIngredients();

    Optional<Ingredient> editIngredient(Integer id, Ingredient ingredient);

    Optional<Ingredient> deleteIngredient(Integer id);

    Path saveIngredientsBackup();

    void uploadIngredientsBackup(MultipartFile file);
}
