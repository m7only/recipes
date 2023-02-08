package com.m7.recipes.controllers;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping({"recipe", "recipe/"})
@Validated
@Tag(name = "API рецептов", description = "CRUD рецептов. Пагинация рецептов. Поиск по ингредиентам.")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Добавление рецепта")
    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @Operation(summary = "Список всех рецептов")
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipe());
    }

    @Operation(summary = "Пагинация", description = "Получение списка рецептов по номеру странице и количеству рецептов на странице")
    @GetMapping(params = {"page", "atpage"})
    public ResponseEntity<List<Recipe>> getPaginatedRecipes(@RequestParam @Positive Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(10) Integer atPage) {
        return ResponseEntity.ok(recipeService.getPaginatedRecipe(page, atPage));
    }

    @Operation(summary = "Поиск рецептов, содержащих крнкретный ингредиент")
    @GetMapping(params = {"i_id"})
    public ResponseEntity<List<Recipe>> getRecipesByIngredientId(@RequestParam(name = "i_id") @PositiveOrZero Integer ingredientId) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredientId(ingredientId));
    }

    @Operation(summary = "Поиск рецептов, содержащие список ингредиентов")
    @GetMapping(params = "byingredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestBody List<Ingredient> byIngredients) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredients(byIngredients));
    }

    @Operation(summary = "Получение рецепта по id")
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @Operation(summary = "Редактирование рецепта")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id,
                                             @RequestBody @Valid Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipe(id, recipe));
    }

    @Operation(summary = "Удаление рецепта по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }
}
