package com.m7.recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // сделано ранее
public class Ingredient implements Formatted {
    @NotBlank
    private String ingredientTitle;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String measurementUnit;

    @Override
    @JsonIgnore
    public String formatted() {
        return ingredientTitle + " — " + quantity + " " + measurementUnit;
    }
}
