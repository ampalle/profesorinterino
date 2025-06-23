package com.profesorinterino.centros.dto;

// Importamos la entidad CentroEducativo que se va a convertir a DTO
import com.profesorinterino.centros.model.CentroEducativo;

/**
 * DTO (Data Transfer Object) para representar los datos de un Centro Educativo.
 * 
 * Sirve para:
 * - Enviar datos a la vista (frontend o API REST) sin exponer directamente la entidad original.
 * - Evitar ciclos infinitos o problemas de carga al usar relaciones (por ejemplo con Localidad o Provincia).
 */
public class CentroEducativoDTO {
    
    // Atributos planos y simples que representarán la información del centro
    private String codigo;
    private String nombre;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String comunidad;
    private String codigoPostal;
    private String telefono;
	private Double latitud;
	private Double longitud;
	private String direccionNormalizada;
    

	/**
     * Constructor vacío obligatorio para que Spring o frameworks puedan crear objetos automáticamente.
     */
    public CentroEducativoDTO() {
    }

    /**
     * Constructor que recibe un objeto CentroEducativo y extrae sus datos principales.
     * Se encarga también de "navegar" por las relaciones para obtener los nombres
     * de la localidad, provincia y comunidad autónoma.
     */
    public CentroEducativoDTO(CentroEducativo centro) {
        this.codigo = centro.getCodigo();
        this.nombre = centro.getDenominacionEspecifica(); 
        this.domicilio = centro.getDomicilio();
        this.codigoPostal = centro.getCp();
        this.telefono = centro.getTelefono();
        this.latitud=centro.getLatitud();
        this.longitud=centro.getLongitud();
        this.direccionNormalizada=centro.getDireccionNormalizada();
    	
        // Extraemos nombres de relaciones si existen (evitamos NullPointerException)
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

    // A continuación, getters y setters: permiten acceder y modificar cada campo del DTO
    // Son necesarios para frameworks como Spring, Jackson (para JSON), etc.

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

	public String getDireccionNormalizada() {
		return direccionNormalizada;
	}

	public void setDireccionNormalizada(String direccionNormalizada) {
		this.direccionNormalizada = direccionNormalizada;
	}
    
}
