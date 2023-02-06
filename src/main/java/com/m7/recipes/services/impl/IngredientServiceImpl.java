package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> RECIPE_STORAGE = new HashMap<>();
    private Integer counter = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return RECIPE_STORAGE.put(counter++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        return RECIPE_STORAGE.getOrDefault(id, null);
    }
}
