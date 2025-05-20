package com.profesorinterino.centros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profesorinterino.centros.service.CentroEducativoService;

@RestController
@RequestMapping("/api/carga")
public class CargaCSVController {

    @Autowired
    private CentroEducativoService centroEducativoService;

    @PostMapping
    public ResponseEntity<String> cargarCSV() {
        centroEducativoService.cargarDatosDesdeCSV("listado_centros.csv","$");
        return ResponseEntity.ok("Carga completada");
    }
}
