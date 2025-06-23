package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Entidad JPA que representa una Localidad.
 * Cada localidad pertenece a una provincia y puede tener varios centros educativos asociados.
 */
@Entity // Esta anotación indica que la clase se mapea a una tabla en la base de datos
public class Localidad {

    /**
     * Clave primaria autogenerada.
     * La estrategia IDENTITY hace que la base de datos genere el valor automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la localidad (ejemplo: "Alcalá de Henares", "Valdemoro")
    private String nombre;

    /**
     * Relación muchos-a-uno con Provincia.
     * Muchas localidades pueden pertenecer a una misma provincia.
     * La columna `provincia_id` es la clave foránea en la tabla de localidades.
     */
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    /**
     * Relación uno-a-muchos con CentroEducativo.
     * Una localidad puede tener varios centros educativos.
     * mappedBy = "localidad" indica que el atributo está definido en la clase CentroEducativo.
     * cascade = ALL aplica operaciones en cascada a los centros si se modifica la localidad.
     */
    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private Set<CentroEducativo> centrosEducativos;

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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Set<CentroEducativo> getCentrosEducativos() {
        return centrosEducativos;
    }

    public void setCentrosEducativos(Set<CentroEducativo> centrosEducativos) {
        this.centrosEducativos = centrosEducativos;
    }
}
