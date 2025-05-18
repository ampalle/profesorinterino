package com.profesorinterino.centros.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private Set<CentroEducativo> centrosEducativos;



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