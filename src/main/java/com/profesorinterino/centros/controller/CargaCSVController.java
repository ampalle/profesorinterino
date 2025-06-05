package com.profesorinterino.centros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profesorinterino.centros.service.CentroEducativoService;

/**
 * Controlador REST que permite lanzar la carga del archivo CSV a través de una petición POST.
 * 
 * Se accede mediante la URL: POST /api/carga
 */
@RestController // Marca esta clase como un controlador REST (devuelve JSON o texto plano, no vistas HTML)
@RequestMapping("/api/carga") // Define la ruta base para este controlador
public class CargaCSVController {

    // Inyectamos el servicio que contiene la lógica de carga del CSV
    @Autowired
    private CentroEducativoService centroEducativoService;

    /**
     * Este endpoint se activa con una petición POST a /api/carga
     * Llama al método de carga del CSV del servicio.
     * 
     * @return una respuesta HTTP 200 OK con un mensaje de éxito
     */
    @PostMapping
    public ResponseEntity<String> cargarCSV() {
        // Ejecutamos la carga desde el archivo "listado_centros.csv" con delimitador $
        centroEducativoService.cargarDatosDesdeCSV("listado_centros.csv", "$");

        // Devolvemos una respuesta HTTP 200 con un mensaje plano
        return ResponseEntity.ok("Carga completada");
    }
}
