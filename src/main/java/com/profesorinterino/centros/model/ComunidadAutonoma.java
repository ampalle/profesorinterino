package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Entidad que representa una Comunidad Autónoma en el modelo de base de datos.
 * Cada comunidad puede tener varias provincias asociadas.
 */
@Entity // Marca esta clase como una tabla JPA en la base de datos
public class ComunidadAutonoma {

    /**
     * Clave primaria autogenerada.
     * La estrategia IDENTITY hace que la base de datos asigne automáticamente un ID al insertar.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la comunidad autónoma (por ejemplo, "Andalucía", "Madrid", "Castilla-La Mancha")
    private String nombre;

    /**
     * Relación uno-a-muchos con la clase Provincia.
     * Una comunidad puede tener varias provincias.
     * 
     * mappedBy = "comunidadAutonoma" indica que la propiedad comunidadAutonoma está en la clase Provincia.
     * cascade = ALL implica que si eliminamos o guardamos una comunidad, se aplicará también a sus provincias.
     */
    @OneToMany(mappedBy = "comunidadAutonoma", cascade = CascadeType.ALL)
    private Set<Provincia> provincias;

    // ---------------------------
    // Constructores
    // ---------------------------

    /**
     * Constructor vacío necesario para JPA (Hibernate lo usa internamente).
     */
    public ComunidadAutonoma() {
    }

    /**
     * Constructor que permite crear una comunidad con un nombre directamente.
     */
    public ComunidadAutonoma(String nombre) {
        this.nombre = nombre;
    }

    // ---------------------------
    // Getters y Setters
    // ---------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(Set<Provincia> provincias) {
        this.provincias = provincias;
    }
}
