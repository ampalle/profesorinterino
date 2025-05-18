package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.model.CentroEducativo;
import com.profesorinterino.centros.repository.CentroEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroEducativoController {

    @Autowired
    private CentroEducativoRepository centroRepo;

    @GetMapping
    public List<CentroEducativo> obtenerPorLocalidad(@RequestParam Long localidadId) {
        return centroRepo.findByLocalidadId(localidadId);
    }
}
