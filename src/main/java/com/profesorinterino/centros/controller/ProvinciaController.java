package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.model.Provincia;
import com.profesorinterino.centros.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaRepository provinciaRepo;

    @GetMapping
    public List<Provincia> obtenerPorComunidad(@RequestParam Long comunidadId) {
        return provinciaRepo.findByComunidadAutonomaId(comunidadId);
    }
}
