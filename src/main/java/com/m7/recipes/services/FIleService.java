package com.m7.recipes.services;

import java.util.Optional;

public interface FIleService {

    void save(String data, String fileName);

    Optional<String> read(String fileName);

    void clean(String fileName);
}
