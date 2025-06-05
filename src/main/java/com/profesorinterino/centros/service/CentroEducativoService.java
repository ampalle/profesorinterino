package com.profesorinterino.centros.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profesorinterino.centros.dto.CentroEducativoDTO;
import com.profesorinterino.centros.model.CentroEducativo;
import com.profesorinterino.centros.model.ComunidadAutonoma;
import com.profesorinterino.centros.model.Localidad;
import com.profesorinterino.centros.model.Provincia;
import com.profesorinterino.centros.repository.CentroEducativoRepository;
import com.profesorinterino.centros.repository.ComunidadAutonomaRepository;
import com.profesorinterino.centros.repository.LocalidadRepository;
import com.profesorinterino.centros.repository.ProvinciaRepository;

/**
 * Servicio que contiene la lógica de negocio relacionada con los centros educativos.
 * Aquí se gestionan la carga de datos desde un CSV y la búsqueda de centros para el frontend.
 */
@Service
public class CentroEducativoService {

    // Inyección de dependencias de los repositorios necesarios
	@Autowired private ComunidadAutonomaRepository comunidadRepo;
	@Autowired private ProvinciaRepository provinciaRepo;
	@Autowired private LocalidadRepository localidadRepo;
	@Autowired private CentroEducativoRepository centroRepo;

    // Constructor explícito (opcional si se usa @Autowired en campos)
    public CentroEducativoService(CentroEducativoRepository centroRepo) {
        this.centroRepo = centroRepo;
    }

    /**
     * Método que carga los datos desde un archivo CSV.
     * Cada línea del archivo representa un centro educativo, con su comunidad, provincia y localidad asociadas.
     * 
     * @param rutaArchivo Nombre del archivo CSV dentro de `resources`
     * @param delimitador Símbolo usado para separar los campos (por ejemplo, "$")
     */
	public void cargarDatosDesdeCSV(String rutaArchivo, String delimitador) {
		try {
		    // Abrimos el archivo CSV desde resources usando UTF-8
			InputStream is = getClass().getClassLoader().getResourceAsStream("listado_centros.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

			reader.readLine(); // Saltamos la primera línea (cabecera del archivo)

			String linea;
			while ((linea = reader.readLine()) != null) {
			    // Separamos los campos usando el delimitador indicado
				String[] campos = linea.split("\\" + delimitador);

				// Extraemos los campos con comprobación de índice para evitar errores
				String nombreComunidad = campos.length > 0 ? campos[0] : "";
				String nombreProvincia = campos.length > 1 ? campos[1] : "";
				String nombreLocalidad = campos.length > 2 ? campos[2] : "";
				String denominacionGenerica = campos.length > 3 ? campos[3] : "";
				String denominacionEspecifica = campos.length > 4 ? campos[4] : "";
				String codigo = campos.length > 5 ? campos[5] : "";
				String domicilio = campos.length > 6 ? campos[6] : "";
				String cp = campos.length > 7 ? campos[7] : "";
				String telefono = campos.length > 8 ? campos[8] : "";

				// Buscar o crear la comunidad autónoma
				ComunidadAutonoma comunidad = comunidadRepo.findByNombre(nombreComunidad)
					.orElseGet(() -> comunidadRepo.save(new ComunidadAutonoma(nombreComunidad)));

				// Buscar o crear la provincia asociada a la comunidad
				Provincia provincia = provinciaRepo
					.findByNombreAndComunidadAutonomaId(nombreProvincia, comunidad.getId())
					.orElseGet(() -> {
						Provincia nuevaProvincia = new Provincia();
						nuevaProvincia.setNombre(nombreProvincia);
						nuevaProvincia.setComunidadAutonoma(comunidad);
						return provinciaRepo.save(nuevaProvincia);
					});

				// Buscar o crear la localidad asociada a la provincia
				Localidad localidad = localidadRepo
					.findByNombreAndProvinciaId(nombreLocalidad, provincia.getId())
					.orElseGet(() -> {
						Localidad nuevaLocalidad = new Localidad();
						nuevaLocalidad.setNombre(nombreLocalidad);
						nuevaLocalidad.setProvincia(provincia);
						return localidadRepo.save(nuevaLocalidad);
					});

				// Si el centro no existe aún en la base de datos, lo creamos
				if (!centroRepo.existsById(codigo)) {
					CentroEducativo centro = new CentroEducativo();
					centro.setCodigo(codigo);
					centro.setDenominacionGenerica(denominacionGenerica);
					centro.setDenominacionEspecifica(denominacionEspecifica);
					centro.setDomicilio(domicilio);
					centro.setCp(cp);
					centro.setTelefono(telefono);
					centro.setLocalidad(localidad);
					centroRepo.save(centro);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Imprime el error si ocurre algo al leer o guardar
		}
	}

    /**
     * Método de búsqueda que devuelve una lista de centros educativos
     * según nombre, provincia y localidad. Convierte cada resultado a DTO.
     * 
     * @param nombre texto a buscar en la denominación del centro
     * @param provinciaId filtro por provincia (puede ser null)
     * @param localidadId filtro por localidad (puede ser null)
     * @return lista de resultados convertidos a DTO
     */
    public List<CentroEducativoDTO> buscarCentros(String nombre, Long provinciaId, Long localidadId) {
        return centroRepo.buscar(nombre, provinciaId, localidadId)
                         .stream()
                         .map(CentroEducativoDTO::new) // convertimos a DTO
                         .collect(Collectors.toList());
    }
}
