package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Esta clase representa una provincia dentro del sistema.
 * Cada provincia:
 * - Pertenece a una comunidad autónoma.
 * - Puede tener varias localidades asociadas.
 */
@Entity // Marca la clase como entidad persistente (tabla en la base de datos)
public class Provincia {

    /**
     * Clave primaria autogenerada.
     * IMPORTANTE: si quieres usar los dos primeros dígitos del código postal como ID,
     * deberás quitar esta anotación y asignar el ID manualmente en CargaInicial.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la provincia (por ejemplo, "Madrid", "Sevilla", "Valencia")
    private String nombre;

    /**
     * Relación muchos-a-uno con ComunidadAutonoma.
     * Muchas provincias pueden pertenecer a una misma comunidad.
     * El campo comunidadAutonoma actúa como clave foránea en la tabla provincias.
     */
    @ManyToOne
    @JoinColumn(name = "comunidad_autonoma_id")
    private ComunidadAutonoma comunidadAutonoma;

    /**
     * Relación uno-a-muchos con Localidad.
     * Una provincia puede tener varias localidades asociadas.
     * mappedBy = "provincia" indica que la propiedad está en la clase Localidad.
     * cascade = ALL permite que al guardar o borrar una provincia se apliquen los cambios en cascada.
     */
    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    private Set<Localidad> localidades;

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

    public ComunidadAutonoma getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(ComunidadAutonoma comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public Set<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(Set<Localidad> localidades) {
        this.localidades = localidades;
    }
}
