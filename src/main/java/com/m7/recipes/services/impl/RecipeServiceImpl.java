package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> RECIPE_STORAGE = new HashMap<>() {{
        put(0, new Recipe());
        put(1, new Recipe());
    }};
    private Integer counter = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return RECIPE_STORAGE.put(counter++, recipe);
    }

    @Override
    public Recipe getRecipeById(Integer id) {
        return RECIPE_STORAGE.getOrDefault(id, null);
    }
}
