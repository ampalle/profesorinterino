package com.profesorinterino.centros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements CommandLineRunner {

    @Autowired
    private CentroEducativoService centroEducativoService;

    @Override
    public void run(String... args) throws Exception {
        //centroEducativoService.cargarDatosDesdeCSV("listado_centros.csv","$");
    }
}
