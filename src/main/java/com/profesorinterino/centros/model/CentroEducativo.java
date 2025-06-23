package com.profesorinterino.centros.model;

// Importamos las anotaciones necesarias para mapear la clase como entidad JPA
import jakarta.persistence.*;

/**
 * Esta clase representa una entidad del modelo de datos: un centro educativo
 * almacenado en la base de datos.
 */
@Entity // Marca esta clase como una entidad que se va a mapear a una tabla de base de
		// datos
public class CentroEducativo {

	// Clave primaria: se usará como identificador único del centro
	@Id
	private String codigo; // Código del centro educativo (por ejemplo, "28012345")

	// Nombre genérico (tipo de centro): "IES", "CEIP", "CP", etc.
	private String denominacionGenerica;

	// Nombre específico del centro: "IES Alonso de Avellaneda"
	private String denominacionEspecifica;

	// Dirección (calle, número, etc.)
	private String domicilio;

	// Código postal del centro (5 dígitos)
	private String cp;

	// Teléfono de contacto
	private String telefono;

	/**
	 * Relación muchos-a-uno con la entidad Localidad. Muchos centros pueden estar
	 * en una misma localidad.
	 * 
	 * Se especifica con @ManyToOne y @JoinColumn para indicar el campo clave
	 * foránea.
	 */
	@ManyToOne
	@JoinColumn(name = "localidad_id") // nombre de la columna en la tabla de centros
	private Localidad localidad;

	
	private Double latitud;
	private Double longitud;
	private String direccionNormalizada;
	
	// -------------------------------
	// Getters y Setters (acceso público a los atributos privados)
	// -------------------------------

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

	public String getDireccionNormalizada() {
		return direccionNormalizada;
	}

	public void setDireccionNormalizada(String direccionNormalizada) {
		this.direccionNormalizada = direccionNormalizada;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

}
