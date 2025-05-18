package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.model.ComunidadAutonoma;
import com.profesorinterino.centros.repository.ComunidadAutonomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunidades")
public class ComunidadAutonomaController {

    @Autowired
    private ComunidadAutonomaRepository comunidadRepo;

    @GetMapping
    public List<ComunidadAutonoma> obtenerTodas() {
        return comunidadRepo.findAll();
    }
}
