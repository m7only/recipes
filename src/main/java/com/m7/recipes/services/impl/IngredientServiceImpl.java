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
    private static Integer counter = 0;
    private final Map<Integer, Ingredient> INGREDIENT_STORAGE = new HashMap<>();

    @Override
    public Ingredient addIngredient(@Valid Ingredient ingredient) {
        return INGREDIENT_STORAGE.put(counter++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        if (!INGREDIENT_STORAGE.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return INGREDIENT_STORAGE.getOrDefault(id, null);
    }
}
