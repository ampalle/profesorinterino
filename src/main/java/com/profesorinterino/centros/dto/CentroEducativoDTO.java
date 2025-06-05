package com.profesorinterino.centros.dto;

import com.profesorinterino.centros.model.CentroEducativo;

public class CentroEducativoDTO {
    private String codigo;
    private String nombre;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String comunidad;
    private String codigoPostal;
    private String telefono;

    public CentroEducativoDTO() {
    }
    
    public CentroEducativoDTO(CentroEducativo centro) {
        this.codigo = centro.getCodigo();
        this.nombre = centro.getDenominacionEspecifica(); // Aquí traducimos
        this.domicilio = centro.getDomicilio();
        this.codigoPostal = centro.getCp();
        this.telefono = centro.getTelefono();
        if (centro.getLocalidad() != null) {
            this.localidad = centro.getLocalidad().getNombre();

            if (centro.getLocalidad().getProvincia() != null) {
                this.provincia = centro.getLocalidad().getProvincia().getNombre();

                if (centro.getLocalidad().getProvincia().getComunidadAutonoma() != null) {
                    this.comunidad = centro.getLocalidad().getProvincia().getComunidadAutonoma().getNombre();
                }
            }
        }
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
}
