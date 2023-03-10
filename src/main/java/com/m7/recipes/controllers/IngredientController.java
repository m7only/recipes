package com.m7.recipes.controllers;


import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingredient/")
@Tag(name = "API ингредиентов", description = "CRUD ингредиентов.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Результат запроса получен"),
        @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
        @ApiResponse(responseCode = "404", description = "Результат запроса = NULL"),
        @ApiResponse(responseCode = "500", description = "Внутренний косяк сервера")
})
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(summary = "Добавление ингредиента")
    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @Operation(summary = "Получение списка всех ингредиентов")
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @Operation(summary = "Получение ингредиента по id")
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByIdIngredient(@PathVariable Integer id) {
        return ResponseEntity.of(ingredientService.getIngredientById(id));
    }

    @Operation(summary = "Изменение ингредиента по id")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer id, @RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.of(ingredientService.editIngredient(id, ingredient));
    }

    @Operation(summary = "Удаление ингердиента по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Integer id) {
        return ResponseEntity.of(ingredientService.deleteIngredient(id));
    }
}
