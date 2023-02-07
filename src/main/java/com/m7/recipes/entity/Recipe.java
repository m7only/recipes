package com.m7.recipes.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Recipe {
    @NotBlank
    private String title;
    @NotNull
    private Integer cookTime;

    List<Ingredient> ingredients;
    Map<Integer, String> instruction;
}
