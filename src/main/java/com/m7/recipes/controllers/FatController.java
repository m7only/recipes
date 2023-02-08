package com.m7.recipes.controllers;

import com.m7.recipes.entity.CodeAddict;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Hidden
public class FatController {
    @GetMapping
    public String index() {
        return "Приложение запущено";
    }

    @GetMapping("info")
    public CodeAddict info() {
        return new CodeAddict();
    }
}
