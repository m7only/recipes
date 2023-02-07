package com.m7.recipes.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Ingredient {
    @NotBlank
    private String ingredientTitle;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String measurementUnit;

}
