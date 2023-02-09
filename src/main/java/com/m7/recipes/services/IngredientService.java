package com.m7.recipes.services;

import com.m7.recipes.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Optional<Ingredient> getIngredientById(Integer id);

    List<Ingredient> getAllIngredients();

    Optional<Ingredient> editIngredient(Integer id, Ingredient ingredient);

    Optional<Ingredient> deleteIngredient(Integer id);
}
