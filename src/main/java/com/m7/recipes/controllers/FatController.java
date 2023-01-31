package com.m7.recipes.controllers;

import com.m7.recipes.entity.CodeAddict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class FatController {
    private List<CodeAddict> codeAddict = new ArrayList<>() {{
        add(new CodeAddict());
    }};

    @GetMapping
    public String index() {
        return "Приложение запущено";
    }

    @GetMapping("info")
    public List<CodeAddict> info() {
        return codeAddict;
    }
}
