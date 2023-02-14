package com.m7.recipes.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data // сделано ранее
public class Recipe implements Formatted {
    @NotBlank
    private String title;
    @NotNull
    private Integer cookTime;

    private List<Ingredient> ingredients;
    private Map<Integer, String> instruction;

    @Override
    public String formatted() {
        StringBuilder result = new StringBuilder();
        result.append(title).append("\n");
        result.append("Время приготовления: ").append(cookTime).append("\n");
        result.append("Ингредиенты:").append("\n");
        ingredients.forEach(ingredient -> result.append("•    ").append(ingredient.formatted()).append("\n"));
        result.append("Инструкция приготовления:").append("\n");
        instruction.forEach((key, value) -> result.append(key).append(". ").append(value).append("\n"));
        return result.toString();
    }
}
