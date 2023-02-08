package com.m7.recipes.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data // сделано ранее
public class Recipe {
    @NotBlank
    private String title;
    @NotNull
    private Integer cookTime;

    private List<Ingredient> ingredients;
    private Map<Integer, String> instruction;
}
