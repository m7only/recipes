package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(Integer id);
}
