package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Provincia {
    @Id
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "comunidad_autonoma_id")
    private ComunidadAutonoma comunidadAutonoma;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    private Set<Localidad> localidades;

	
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