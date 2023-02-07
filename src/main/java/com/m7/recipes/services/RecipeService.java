package com.m7.recipes.services;

import com.m7.recipes.entity.Recipe;
import jakarta.validation.Valid;

public interface RecipeService {
    Recipe addRecipe(@Valid Recipe recipe);

    Recipe getRecipeById(Integer id);
}
