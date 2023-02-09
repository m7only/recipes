package com.m7.recipes.controllers;

import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.entity.Recipe;
import com.m7.recipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipe/")
@Validated
@Tag(name = "API рецептов", description = "CRUD рецептов. Пагинация рецептов. Поиск по ингредиентам.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Результат запроса получен"),
        @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
        @ApiResponse(responseCode = "404", description = "Результат запроса = NULL"),
        @ApiResponse(responseCode = "500", description = "Внутренний косяк сервера")
})
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
    @GetMapping(value = "showRecipes", params = {"page", "atpage"})
    public ResponseEntity<List<Recipe>> getPaginatedRecipes(@RequestParam @Positive Integer page,
                                                            @RequestParam(defaultValue = "10", required = false) @Min(10) Integer atPage) {
        return ResponseEntity.ok(recipeService.getPaginatedRecipe(page, atPage));
    }

    @Operation(summary = "Поиск рецептов, содержащих крнкретный ингредиент")
    @GetMapping(value = "findByIngredient", params = {"i_id"})
    public ResponseEntity<List<Recipe>> getRecipesByIngredientId(@RequestParam(name = "i_id") @PositiveOrZero Integer ingredientId) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredientId(ingredientId));
    }

    @Operation(summary = "Поиск рецептов, содержащие список ингредиентов")
    @GetMapping(value = "findByIngredients", params = "byingredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestBody List<Ingredient> byIngredients) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredients(byIngredients));
    }

    @Operation(summary = "Получение рецепта по id")
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Редактирование рецепта")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id,
                                             @RequestBody @Valid Recipe recipe) {
        Recipe resultRecipe = recipeService.editRecipe(id, recipe);
        return resultRecipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удаление рецепта по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer id) {
        Recipe recipe = recipeService.deleteRecipe(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }
}
