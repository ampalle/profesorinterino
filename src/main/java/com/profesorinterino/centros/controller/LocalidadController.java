package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.model.Localidad;
import com.profesorinterino.centros.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadRepository localidadRepo;

    @GetMapping
    public List<Localidad> obtenerPorProvincia(@RequestParam Long provinciaId) {
        return localidadRepo.findByProvinciaId(provinciaId);
    }
}
