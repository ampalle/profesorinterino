package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class ComunidadAutonoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "comunidadAutonoma", cascade = CascadeType.ALL)
    private Set<Provincia> provincias;


    // Constructor por defecto
    public ComunidadAutonoma() {
    }

    // Constructor con parámetro nombre
    public ComunidadAutonoma(String nombre) {
        this.nombre = nombre;
    }
    // Getters y setters
    

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