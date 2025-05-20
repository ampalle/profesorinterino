package com.profesorinterino.centros.service;

import com.profesorinterino.centros.model.*;
import com.profesorinterino.centros.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class CentroEducativoService {

	@Autowired
	private ComunidadAutonomaRepository comunidadRepo;

	@Autowired
	private ProvinciaRepository provinciaRepo;

	@Autowired
	private LocalidadRepository localidadRepo;

	@Autowired
	private CentroEducativoRepository centroRepo;

	public void cargarDatosDesdeCSV(String rutaArchivo, String delimitador) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("listado_centros.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String linea;
			reader.readLine(); // Saltar encabezado
			while ((linea = reader.readLine()) != null) {
				String[] campos = linea.split("\\" + delimitador);
				String nombreComunidad = campos.length > 0 ? campos[0] : "";
				String nombreProvincia = campos.length > 1 ? campos[1] : "";
				String nombreLocalidad = campos.length > 2 ? campos[2] : "";
				String denominacionGenerica = campos.length > 3 ? campos[3] : "";
				String denominacionEspecifica = campos.length > 4 ? campos[4] : "";
				String codigo = campos.length > 5 ? campos[5] : "";
				String domicilio = campos.length > 6 ? campos[6] : "";
				String cp = campos.length > 7 ? campos[7] : "";
				String telefono = campos.length > 8 ? campos[8] : "";
				ComunidadAutonoma comunidad = comunidadRepo.findByNombre(nombreComunidad)
						.orElseGet(() -> comunidadRepo.save(new ComunidadAutonoma(nombreComunidad)));

				Provincia provincia = provinciaRepo
						.findByNombreAndComunidadAutonomaId(nombreProvincia, comunidad.getId()).orElseGet(() -> {
							Provincia nuevaProvincia = new Provincia();
							nuevaProvincia.setNombre(nombreProvincia);
							nuevaProvincia.setComunidadAutonoma(comunidad);
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
					centro.setTelefono(telefono);
					centro.setLocalidad(localidad);
					centroRepo.save(centro);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
