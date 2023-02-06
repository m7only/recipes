package com.m7.recipes.entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Recipe {
    String title;
    Integer cookTime;
    List<Ingredient> ingredients;
    Map<Integer, String> instruction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public List<Ingredient> getIndigrients() {
        return ingredients;
    }

    public void setIndigrients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<Integer, String> getInstruction() {
        return instruction;
    }

    public void setInstruction(Map<Integer, String> instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return title.equals(recipe.title) && cookTime.equals(recipe.cookTime) && ingredients.equals(recipe.ingredients) && instruction.equals(recipe.instruction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cookTime, ingredients, instruction);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", cookTime=" + cookTime +
                ", indigrients=" + ingredients +
                ", instruction=" + instruction +
                '}';
    }
}
