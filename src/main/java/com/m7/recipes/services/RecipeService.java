package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    List<Recipe> getAllRecipe();

    Optional<Recipe> getRecipeById(Integer id);

    Optional<Recipe> editRecipe(Integer id, Recipe recipe);

    Optional<Recipe> deleteRecipe(Integer id);

    List<Recipe> getPaginatedRecipe(Integer page, Integer atPage);

    List<Recipe> getRecipesByIngredientId(Integer ingredientId);

    List<Recipe> getRecipesByIngredients(List<Ingredient> ingredients);
}
