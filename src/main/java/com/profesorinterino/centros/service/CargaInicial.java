package com.profesorinterino.centros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase se ejecuta automáticamente cuando arranca la aplicación.
 * Sirve para cargar los datos iniciales desde un archivo CSV a la base de datos.
 * 
 * Implementa la interfaz CommandLineRunner, que tiene un único método `run()`.
 * Spring Boot ejecuta este método al iniciar el programa.
 */
@Component // Marca la clase como un componente para que Spring la detecte y la gestione automáticamente
public class CargaInicial implements CommandLineRunner {

    /**
     * Servicio que se encarga de cargar los datos de centros educativos.
     * Se inyecta automáticamente con @Autowired.
     */
    @Autowired
    private CentroEducativoService centroEducativoService;

    /**
     * Este método se ejecuta al iniciar la aplicación.
     * Llama al método del servicio que carga los centros desde un archivo CSV.
     * 
     * @param args Argumentos del programa (normalmente vacíos)
     * @throws Exception si hay errores de lectura o formato
     */
    @Override
    public void run(String... args) throws Exception {
        centroEducativoService.cargarDatosDesdeCSV("centros_con_coordenadas.csv","$");
        // Cargamos los datos desde el archivo CSV "listado_centros.csv", usando $ como separador
    }
}
