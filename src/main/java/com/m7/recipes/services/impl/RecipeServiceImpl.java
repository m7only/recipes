package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
@Validated
public class RecipeServiceImpl implements RecipeService {
    public static Integer counter = 0;
    private final Map<Integer, Recipe> RECIPE_STORAGE = new HashMap<>();

    @Override
    public Recipe addRecipe(@Valid Recipe recipe) {
        return RECIPE_STORAGE.put(counter++, recipe);
    }

    @Override
    public Recipe getRecipeById(Integer id) {
        if (!RECIPE_STORAGE.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return RECIPE_STORAGE.get(id);
    }
}
