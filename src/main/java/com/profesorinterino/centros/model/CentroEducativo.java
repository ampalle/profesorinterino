package com.profesorinterino.centros.model;

import jakarta.persistence.*;

@Entity
public class CentroEducativo {
    @Id
    private String codigo;

    private String denominacionGenerica;
    private String denominacionEspecifica;
    private String domicilio;
    private String cp;
   
	private String telefono;

    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    // Getters y setters
    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDenominacionGenerica() {
		return denominacionGenerica;
	}

	public void setDenominacionGenerica(String denominacionGenerica) {
		this.denominacionGenerica = denominacionGenerica;
	}

	public String getDenominacionEspecifica() {
		return denominacionEspecifica;
	}

	public void setDenominacionEspecifica(String denominacionEspecifica) {
		this.denominacionEspecifica = denominacionEspecifica;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

}
