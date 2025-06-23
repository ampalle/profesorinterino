package com.profesorinterino.centros.controller;

import com.profesorinterino.centros.dto.CentroEducativoDTO;
import com.profesorinterino.centros.service.CentroEducativoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que permite buscar centros educativos.
 * Responde a peticiones HTTP en la ruta /api/centros.
 */
@RestController // Marca esta clase como un controlador REST (devuelve datos en formato JSON)
@RequestMapping("/api/centros") // Ruta base para todos los endpoints de esta clase
@CrossOrigin // Permite que el frontend (React, Angular, etc.) haga peticiones desde otro dominio o puerto
public class CentroEducativoController {

    // Inyección del servicio que contiene la lógica de negocio
    private final CentroEducativoService centroService;

    /**
     * Constructor que recibe el servicio por inyección.
     * Spring lo invoca automáticamente cuando crea el controlador.
     */
    public CentroEducativoController(CentroEducativoService centroService) {
        this.centroService = centroService;
    }

    /**
     * Endpoint GET que permite buscar centros educativos.
     * La ruta completa sería: /api/centros/buscar
     * 
     * Recibe parámetros opcionales:
     * - nombre: parte del nombre del centro (texto)
     * - provinciaId: id de la provincia
     * - localidadId: id de la localidad
     * 
     * Si un parámetro es null, no se aplica ese filtro.
     * 
     * @return lista de centros encontrados, convertidos a DTO
     */
    @GetMapping("/buscar")
    public List<CentroEducativoDTO> buscarCentros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long provinciaId,
            @RequestParam(required = false) Long localidadId
    ) {
        // Llamamos al servicio para obtener los resultados según los filtros indicados
        return centroService.buscarCentros(nombre, provinciaId, localidadId);
    }
}
