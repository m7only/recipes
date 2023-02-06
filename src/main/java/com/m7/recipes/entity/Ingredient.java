package com.m7.recipes.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Ingredient {
    @NotBlank
    String ingredientTitle;
    @NotNull
    Integer quantity;
    @NotBlank
    String measurementUnit;

    public String getIngredientTitle() {
        return ingredientTitle;
    }

    public void setIngredientTitle(String ingredientTitle) {
        this.ingredientTitle = ingredientTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientTitle.equals(that.ingredientTitle) && quantity.equals(that.quantity) && measurementUnit.equals(that.measurementUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientTitle, quantity, measurementUnit);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientTitle='" + ingredientTitle + '\'' +
                ", quantity=" + quantity +
                ", measurementUnit='" + measurementUnit + '\'' +
                '}';
    }
}
