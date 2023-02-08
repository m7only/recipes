package com.m7.recipes.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data // сделано ранее
public class Ingredient {
    @NotBlank
    private String ingredientTitle;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String measurementUnit;

}
