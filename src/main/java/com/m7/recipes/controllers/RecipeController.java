package com.m7.recipes.controllers;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"recipe", "recipe/"})
@Validated
public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipe());
    }

    @GetMapping(params = {"page", "atpage"})
    public ResponseEntity<List<Recipe>> getPaginatedRecipes(@RequestParam @Positive Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(10) Integer atPage) {
        return ResponseEntity.ok(recipeService.getPaginatedRecipe(page, atPage));
    }

    @GetMapping(params = {"i_id"})
    public ResponseEntity<List<Recipe>> getRecipesByIngredientId(@RequestParam(name = "i_id") @PositiveOrZero Integer ingredientId) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredientId(ingredientId));
    }

    @GetMapping(params = "ingredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestBody List<Ingredient> ingredients) {
        System.out.println("asd");
        return ResponseEntity.ok(recipeService.getRecipesByIngredients(ingredients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id,
                                             @RequestBody @Valid Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }
}
