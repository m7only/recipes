package com.m7.recipes.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.BackupService;
import com.m7.recipes.services.RecipeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class RecipeServiceImpl implements RecipeService {
    private static Integer counter = 0;
    private Map<Integer, Recipe> recipeStorage = new HashMap<>();

    @Value("${recipe.backup.file.name}")
    private String fileName;
    private final BackupService backupService;

    public RecipeServiceImpl(BackupService backupService) {
        this.backupService = backupService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeStorage.put(counter++, recipe);
        backupService.SaveMap(recipeStorage, fileName);
        return recipe;
    }

    @PostConstruct
    private void backupLoad() {
        ObjectMapper objectMapper = new ObjectMapper();
        recipeStorage = backupService.LoadMap(fileName)
                .orElse(new HashMap<Integer, Recipe>())
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> objectMapper.convertValue(entry.getKey(), Integer.class),
                        entry -> objectMapper.convertValue(entry.getValue(), Recipe.class)
                ));

        counter = recipeStorage.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);
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
        backupService.SaveMap(recipeStorage, fileName);
        return Optional.ofNullable(recipe);
    }

    @Override
    public Optional<Recipe> deleteRecipe(Integer id) {
        if (!recipeStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Recipe recipe = recipeStorage.remove(id);
        backupService.SaveMap(recipeStorage, fileName);
        return Optional.ofNullable(recipe);
    }
}
