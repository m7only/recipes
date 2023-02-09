package com.m7.recipes.controllers;


import com.m7.recipes.entity.Ingredient;
import com.m7.recipes.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("ingredient/")
@Tag(name = "API ингредиентов", description = "CRUD ингредиентов.")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(summary = "Добавление ингредиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Введены не валидные данные")
    })
    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @Operation(summary = "Получение списка всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты получены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
            })
    })
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @Operation(summary = "Получение ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент получен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Отсутствует ингредиент с введенным ID"),
            @ApiResponse(responseCode = "404", description = "Ингредиент с введенным ID = NULL")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByIdIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Изменение ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент изменен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Отсутствует ингредиент с введенным ID или данные введены не верно"),
            @ApiResponse(responseCode = "404", description = "Ингредиент с введенным ID = NULL")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer id, @RequestBody @Valid Ingredient ingredient) {
        Ingredient resultIngredient = ingredientService.editIngredient(id, ingredient);
        return resultIngredient != null ? ResponseEntity.ok(resultIngredient) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удаление ингердиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Отсутствует ингредиент с введенным ID"),
            @ApiResponse(responseCode = "404", description = "Ингредиент с введенным ID = NULL")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.deleteIngredient(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }
}
