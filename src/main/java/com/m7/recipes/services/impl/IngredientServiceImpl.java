package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
@Validated
public class IngredientServiceImpl implements IngredientService {
    public static Integer counter = 0;
    private final Map<Integer, Ingredient> RECIPE_STORAGE = new HashMap<>();

    @Override
    public Ingredient addIngredient(@Valid Ingredient ingredient) {
        return RECIPE_STORAGE.put(counter++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        return RECIPE_STORAGE.getOrDefault(id, null);
    }
}
