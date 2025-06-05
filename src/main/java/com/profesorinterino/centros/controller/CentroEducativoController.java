package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.dto.CentroEducativoDTO;
import com.profesorinterino.centros.model.CentroEducativo;
import com.profesorinterino.centros.service.CentroEducativoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
@CrossOrigin
public class CentroEducativoController {

    private final CentroEducativoService centroService;

    public CentroEducativoController(CentroEducativoService centroService) {
        this.centroService = centroService;
    }

    @GetMapping("/buscar")
    public List<CentroEducativoDTO> buscarCentros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long provinciaId,
            @RequestParam(required = false) Long localidadId
    ) {
        return centroService.buscarCentros(nombre, provinciaId, localidadId);
    }
}
