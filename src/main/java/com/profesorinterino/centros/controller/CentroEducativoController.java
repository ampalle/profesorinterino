package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.model.CentroEducativo;
import com.profesorinterino.centros.repository.CentroEducativoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
@CrossOrigin(origins = "*")
public class CentroEducativoController {

    @Autowired
    private CentroEducativoRepository centroRepo;

    @GetMapping("/buscar")
    public List<CentroEducativo> buscarCentros(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) Long provinciaId,
        @RequestParam(required = false) Long localidadId
    ) {
        return centroRepo.buscar(nombre, provinciaId, localidadId);
    }
}
