package com.m7.recipes.controllers;


import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"ingredient", "ingredient/"})
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByIdIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer id, @RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }
}
