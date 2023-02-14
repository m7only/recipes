package com.m7.recipes.controllers;

import com.m7.recipes.services.IngredientService;
import com.m7.recipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/backup")
@Tag(name = "API backup рецептов", description = "Экспорт\\импорт рецептов, ингредиентов")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Результат запроса получен"),
        @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
        @ApiResponse(responseCode = "404", description = "Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка")
})
public class BackupController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public BackupController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @Operation(summary = "Выгрузка текущих данных по рецептам")
    @GetMapping("/recipes")
    public ResponseEntity<InputStreamResource> getRecipesBackup() throws IOException {
        Path path = recipeService.saveRecipesBackup();
        return path != null
                ? ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(Files.size(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString())
                .body(new InputStreamResource(Files.newInputStream(path)))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Загрузка данных по рецетам")
    @PostMapping(value = "/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipesBackup(@RequestParam MultipartFile file) {
        recipeService.uploadRecipesBackup(file);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Выгрузка текущих данных по ингредиентам")
    @GetMapping("/ingredients")
    public ResponseEntity<InputStreamResource> getIngredientsBackup() throws IOException {
        Path path = ingredientService.saveIngredientsBackup();
        return path != null
                ? ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(Files.size(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString())
                .body(new InputStreamResource(Files.newInputStream(path)))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Загрузка данных по ингредиентам")
    @PostMapping(value = "/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientsBackup(@RequestParam MultipartFile file) {
        ingredientService.uploadIngredientsBackup(file);
        return ResponseEntity.ok().build();
    }
}