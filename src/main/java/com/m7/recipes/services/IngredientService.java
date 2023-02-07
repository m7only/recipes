package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(Integer id);

    List<Ingredient> getAllIngredients();

    Ingredient editIngredient(Integer id, Ingredient ingredient);

    Ingredient deleteIngredient(Integer id);
}
