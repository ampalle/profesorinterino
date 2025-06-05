package com.profesorinterino;

// Importamos la clase principal de Spring Boot que permite arrancar la aplicación
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esta clase es el punto de entrada de la aplicación Spring Boot.
 * Se ejecuta cuando iniciamos el programa.
 */
@SpringBootApplication // Esta anotación indica que es una aplicación Spring Boot.
// Incluye automáticamente configuración, escaneo de componentes y más.
public class ProfesorInterinoApplication {

    /**
     * Método principal (main), que se ejecuta al iniciar el programa.
     * Llama a SpringApplication.run(), que arranca el servidor embebido
     * y pone en marcha todo el sistema.
     *
     * @param args Argumentos que podrían pasarse al arrancar (no se usan normalmente)
     */
    public static void main(String[] args) {
        // Este método lanza toda la aplicación Spring Boot.
        SpringApplication.run(ProfesorInterinoApplication.class, args);
    }
}
