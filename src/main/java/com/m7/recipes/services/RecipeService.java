package com.m7.recipes.services;

import com.m7.recipes.entity.Recipe;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipeById(Integer id);
}
