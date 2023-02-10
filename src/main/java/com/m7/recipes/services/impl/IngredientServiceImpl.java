package com.m7.recipes.services.impl;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Validated
public class IngredientServiceImpl implements IngredientService {
    public static Integer counter = 0;
    private final Map<Integer, Ingredient> ingredientStorage = new HashMap<>();

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientStorage.put(counter++, ingredient);
    }

    @Override
    public Optional<Ingredient> getIngredientById(Integer id) {
        Validate.notNull(id);
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return Optional.ofNullable(ingredientStorage.get(id));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientStorage.values().stream().toList();
    }

    @Override
    public Optional<Ingredient> editIngredient(Integer id, Ingredient ingredient) {
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        ingredientStorage.put(id, ingredient);
        return Optional.ofNullable(ingredient);
    }

    @Override
    public Optional<Ingredient> deleteIngredient(Integer id) {
        if (!ingredientStorage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return Optional.ofNullable(ingredientStorage.remove(id));
    }
}
