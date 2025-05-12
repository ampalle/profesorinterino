package com.profesorinterino.controller;

import com.profesorinterino.model.CentroEducativo;
import com.profesorinterino.repository.CentroEducativoRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centros")
public class CentroEducativoController {

    private final CentroEducativoRepository repository;

    public CentroEducativoController(CentroEducativoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CentroEducativo> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public CentroEducativo create(@RequestBody CentroEducativo centro) {
        return repository.save(centro);
    }
}
