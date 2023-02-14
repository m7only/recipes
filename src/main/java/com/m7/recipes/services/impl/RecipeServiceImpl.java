package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.BackupService;
import com.m7.recipes.services.IngredientService;
import com.m7.recipes.services.RecipeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class RecipeServiceImpl implements RecipeService {
    private static Integer counter = 0;
    private final BackupService backupService;
    private final IngredientService ingredientService;
    private Map<Integer, Recipe> recipeStorage = new HashMap<>();
    @Value("${recipe.backup.file.name}")
    private String fileName;

    public RecipeServiceImpl(BackupService backupService, IngredientService ingredientService) {
        this.backupService = backupService;
        this.ingredientService = ingredientService;
    }

    @PostConstruct
    private void backupLoad() {
        recipeStorage = backupService
                .loadBackup(Integer.class, Recipe.class, fileName)
                .orElse(recipeStorage);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeStorage.put(counter++, recipe);
        saveRecipesBackup();
        ingredientService.addIngredient(recipe.getIngredients());
        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return recipeStorage.values().stream().toList();
    }

    @Override
    public List<Recipe> getPaginatedRecipe(Integer page, Integer atPage) {
        int endRecipeId = page * atPage - 1;
        int startRecipeId = endRecipeId - (atPage - 1);
        return recipeStorage.entrySet().stream()
                .filter(entry -> entry.getKey() >= startRecipeId && entry.getKey() <= endRecipeId)
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getRecipesByIngredientId(Integer ingredientId) {
        return recipeStorage.values().stream()
                .filter(recipe -> recipe.getIngredients().get(ingredientId) != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getRecipesByIngredients(List<Ingredient> ingredients) {
        return recipeStorage.values().stream()
                .filter(recipe -> new HashSet<>(recipe.getIngredients()).containsAll(ingredients))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> getRecipeById(Integer id) {
        if (!recipeStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return Optional.ofNullable(recipeStorage.get(id));
    }

    @Override
    public Optional<Recipe> editRecipe(Integer id, Recipe recipe) {
        if (!recipeStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        recipeStorage.put(id, recipe);
        saveRecipesBackup();
        ingredientService.addIngredient(recipe.getIngredients());
        return Optional.ofNullable(recipe);
    }

    @Override
    public Optional<Recipe> deleteRecipe(Integer id) {
        if (!recipeStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Recipe recipe = recipeStorage.remove(id);
        saveRecipesBackup();
        ingredientService.addIngredient(recipe.getIngredients());
        return Optional.ofNullable(recipe);
    }

    @Override
    public Path saveRecipesBackup() {
        return backupService.saveBackup(recipeStorage, fileName);
    }

    @Override
    public void uploadRecipesBackup(MultipartFile file) {
        recipeStorage = backupService.uploadBackupFile(Integer.class, Recipe.class, file, fileName).orElse(recipeStorage);
        recipeStorage.values().stream()
                .toList()
                .forEach(recipe -> ingredientService.addIngredient(recipe.getIngredients()));
    }
}
