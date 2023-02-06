package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;
import jakarta.validation.Valid;

public interface IngredientService {
    Ingredient addIngredient(@Valid Ingredient ingredient);

    Ingredient getIngredientById(Integer id);
}
