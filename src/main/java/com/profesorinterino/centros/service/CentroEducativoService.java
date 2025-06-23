package com.profesorinterino.centros.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
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

@Service
public class CentroEducativoService {

	private static final Map<String, Integer> CODIGOS_PROVINCIA = Map.ofEntries(
		    Map.entry("Almería", 4),
		    Map.entry("Cádiz", 11),
		    Map.entry("Córdoba", 14),
		    Map.entry("Granada", 18),
		    Map.entry("Huelva", 21),
		    Map.entry("Jaén", 23),
		    Map.entry("Málaga", 29),
		    Map.entry("Sevilla", 41),
		    Map.entry("Huesca", 22),
		    Map.entry("Teruel", 44),
		    Map.entry("Zaragoza", 50),
		    Map.entry("Asturias", 33),
		    Map.entry("Illes Balears", 7),
		    Map.entry("Las Palmas", 35),
		    Map.entry("Santa Cruz de Tenerife", 38),
		    Map.entry("Cantabria", 39),
		    Map.entry("Ávila", 5),
		    Map.entry("Burgos", 9),
		    Map.entry("León", 24),
		    Map.entry("Palencia", 34),
		    Map.entry("Salamanca", 37),
		    Map.entry("Segovia", 40),
		    Map.entry("Soria", 42),
		    Map.entry("Valladolid", 47),
		    Map.entry("Zamora", 49),
		    Map.entry("Albacete", 2),
		    Map.entry("Ciudad Real", 13),
		    Map.entry("Cuenca", 16),
		    Map.entry("Guadalajara", 19),
		    Map.entry("Toledo", 45),
		    Map.entry("Barcelona", 8),
		    Map.entry("Girona", 17),
		    Map.entry("Lleida", 25),
		    Map.entry("Tarragona", 43),
		    Map.entry("Badajoz", 6),
		    Map.entry("Cáceres", 10),
		    Map.entry("A Coruña", 15),
		    Map.entry("Lugo", 27),
		    Map.entry("Ourense", 32),
		    Map.entry("Pontevedra", 36),
		    Map.entry("La Rioja", 26),
		    Map.entry("Madrid", 28),
		    Map.entry("Murcia", 30),
		    Map.entry("Navarra", 31),
		    Map.entry("Alicante/Alacant", 3),
		    Map.entry("Castellón/Castelló", 12),
		    Map.entry("Valencia/València", 46),
		    Map.entry("Araba/Álava", 1),
		    Map.entry("Bizkaia", 48),
		    Map.entry("Gipuzkoa", 20),
		    Map.entry("Ceuta", 51),
		    Map.entry("Melilla", 52)
		);
	
	private static final Map<String, Integer> CODIGOS_COMUNIDAD = Map.ofEntries(
		    Map.entry("COMUNIDAD AUTÓNOMA DE ANDALUCÍA", 1),
		    Map.entry("COMUNIDAD AUTÓNOMA DE ARAGÓN", 2),
		    Map.entry("PRINCIPADO DE ASTURIAS", 3),
		    Map.entry("COMUNIDAD AUTÓNOMA DE LAS ILLES BALEARS", 4),
		    Map.entry("COMUNIDAD AUTÓNOMA DE CANARIAS", 5),
		    Map.entry("COMUNIDAD AUTÓNOMA DE CANTABRIA", 6),
		    Map.entry("COMUNIDAD DE CASTILLA Y LEÓN", 7),
		    Map.entry("COMUNIDAD AUTÓNOMA DE CASTILLA-LA MANCHA", 8),
		    Map.entry("COMUNIDAD AUTÓNOMA DE CATALUÑA", 9),
		    Map.entry("COMUNIDAD VALENCIANA", 10),
		    Map.entry("COMUNIDAD AUTÓNOMA DE EXTREMADURA", 11),
		    Map.entry("COMUNIDAD AUTÓNOMA DE GALICIA", 12),
		    Map.entry("COMUNIDAD DE MADRID", 13),
		    Map.entry("REGIÓN DE MURCIA", 14),
		    Map.entry("COMUNIDAD FORAL DE NAVARRA", 15),
		    Map.entry("COMUNIDAD AUTÓNOMA DE LA RIOJA", 16),
		    Map.entry("COMUNIDAD AUTÓNOMA DEL PAÍS VASCO", 17),
		    Map.entry("CIUDAD DE CEUTA", 18),
		    Map.entry("CIUDAD DE MELILLA", 19)
		);
	
	@Autowired
	private ComunidadAutonomaRepository comunidadRepo;

	@Autowired
	private ProvinciaRepository provinciaRepo;

	@Autowired
	private LocalidadRepository localidadRepo;

	@Autowired
	private CentroEducativoRepository centroRepo;

    public CentroEducativoService(CentroEducativoRepository centroRepo) {
        this.centroRepo = centroRepo;
    }
	
	public void cargarDatosDesdeCSV(String rutaArchivo, String delimitador) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			String linea;
			reader.readLine(); // Saltar encabezado
			while ((linea = reader.readLine()) != null) {
				if (linea.length()<=1) continue;
				String[] campos = linea.split("\\" + delimitador);
				String nombreComunidad = campos.length > 0 ? campos[0] : "";
				String nombreProvincia = campos.length > 1 ? campos[1] : "";
				String nombreLocalidad = campos.length > 2 ? campos[2] : "";
				String denominacionGenerica = campos.length > 3 ? campos[3] : "";
				String denominacionEspecifica = campos.length > 4 ? campos[4] : "";
				String codigo = campos.length > 5 ? campos[5] : "";
				//String naturaleza= campos.length > 6 ? campos[6] : "";
				String domicilio = campos.length > 6 ? campos[6] : "";
				String cp = campos.length > 7 ? campos[7] : "";
				String telefono = campos.length > 8 ? campos[8] : "";
				Double latitud = campos.length > 9 && !campos[9].isBlank() ? Double.valueOf(campos[9]) : null;
				Double longitud = campos.length > 10 && !campos[10].isBlank() ? Double.valueOf(campos[10]) : null;
				String direccionNormalizada = campos.length > 11 ? campos[11] : "";
				
				ComunidadAutonoma comunidad = comunidadRepo.findByNombre(nombreComunidad)
						.orElseGet(() -> {
							ComunidadAutonoma nueva = new ComunidadAutonoma();
							nueva.setNombre(nombreComunidad);

							if (CODIGOS_COMUNIDAD.containsKey(nombreComunidad)) {
								nueva.setId(CODIGOS_COMUNIDAD.get(nombreComunidad).longValue());
							}else {
					            // Por seguridad, puedes lanzar una excepción si no se encuentra el código
					            throw new RuntimeException(" Código no encontrado para comunidad: " + nombreComunidad);
					        }

							return comunidadRepo.save(nueva);
						});
				Provincia provincia = provinciaRepo
						.findByNombreAndComunidadAutonomaId(nombreProvincia, comunidad.getId()).orElseGet(() -> {
							Provincia nuevaProvincia = new Provincia();
							nuevaProvincia.setNombre(nombreProvincia);
							nuevaProvincia.setComunidadAutonoma(comunidad);
							
							// Establecer ID estándar si existe
							if (CODIGOS_PROVINCIA.containsKey(nombreProvincia)) {
								nuevaProvincia.setId(CODIGOS_PROVINCIA.get(nombreProvincia).longValue());
							}
							
							return provinciaRepo.save(nuevaProvincia);
						});

				Localidad localidad = localidadRepo.findByNombreAndProvinciaId(nombreLocalidad, provincia.getId())
						.orElseGet(() -> {
							Localidad nuevaLocalidad = new Localidad();
							nuevaLocalidad.setNombre(nombreLocalidad);
							nuevaLocalidad.setProvincia(provincia);
							return localidadRepo.save(nuevaLocalidad);
						});

				if (!centroRepo.existsById(codigo)) {
					CentroEducativo centro = new CentroEducativo();
					centro.setCodigo(codigo);
					centro.setDenominacionGenerica(denominacionGenerica);
					centro.setDenominacionEspecifica(denominacionEspecifica);
					centro.setDomicilio(domicilio);
					centro.setCp(cp);
					//centro.setNaturaleza(naturaleza);
					centro.setTelefono(telefono);
					centro.setLocalidad(localidad);
					centro.setDireccionNormalizada(
						    (direccionNormalizada != null && !direccionNormalizada.isBlank()) ? direccionNormalizada : domicilio
						);
					centro.setLatitud(latitud);
					centro.setLongitud(longitud);
					centroRepo.save(centro);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public List<CentroEducativoDTO> buscarCentros(String nombre, Long provinciaId, Long localidadId) {
        return centroRepo.buscar(nombre, provinciaId, localidadId)
                         .stream()
                         .map(CentroEducativoDTO::new)
                         .collect(Collectors.toList());
    }
}
