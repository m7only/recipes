package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    List<Recipe> getAllRecipe();

    Recipe getRecipeById(Integer id);

    Recipe editRecipe(Integer id, Recipe recipe);

    Recipe deleteRecipe(Integer id);

    List<Recipe> getPaginatedRecipe(Integer page, Integer atPage);

    List<Recipe> getRecipesByIngredientId(Integer ingredientId);

    List<Recipe> getRecipesByIngredients(List<Ingredient> ingredients);
}
