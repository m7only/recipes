package com.m7.recipes.controllers;


import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"ingredient", "ingredient/"})
@Tag(name = "API ингредиентов", description = "CRUD ингредиентов.")
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
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @Operation(summary = "Изменение ингредиента по id")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer id, @RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }

    @Operation(summary = "Удаление ингердиента по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }
}
