package com.profesorinterino.centros.repository;

import com.profesorinterino.centros.model.CentroEducativo;
import com.profesorinterino.centros.model.ComunidadAutonoma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceder a los datos de la entidad CentroEducativo. Extiende
 * JpaRepository, lo que permite usar métodos como findAll(), save(),
 * deleteById() sin implementarlos.
 * 
 * Además, definimos un método personalizado llamado buscar() con una consulta
 * JPQL dinámica.
 */
public interface CentroEducativoRepository extends JpaRepository<CentroEducativo, String> {

	/**
	 * Método personalizado para buscar centros educativos de forma flexible.
	 * 
	 * Esta consulta permite filtrar por: - Parte del nombre del centro
	 * (denominacionEspecifica) - Provincia (por ID) - Localidad (por ID)
	 * 
	 * Si alguno de los parámetros es null, no se aplica ese filtro. Por ejemplo, si
	 * provinciaId == null, no se filtra por provincia.
	 * 
	 * LOWER(...): convierte el texto a minúsculas para que la búsqueda no distinga
	 * mayúsculas/minúsculas. CONCAT('%', :nombre, '%'): permite buscar si el nombre
	 * contiene el texto (búsqueda parcial).
	 */
	@Query("""
			 SELECT c FROM CentroEducativo c
			 WHERE (:nombre IS NULL OR LOWER(c.denominacionEspecifica) LIKE LOWER(CONCAT('%', :nombre, '%')))
			   AND (:provinciaId IS NULL OR c.localidad.provincia.id = :provinciaId)
			   AND (:localidadId IS NULL OR c.localidad.id = :localidadId)
			""")
	List<CentroEducativo> buscar(@Param("nombre") String nombre, @Param("provinciaId") Long provinciaId,
			@Param("localidadId") Long localidadId);



	@Query(value = """
		    SELECT
		        c.*,
		        ST_Distance(
		            c.geom,
		            CAST(ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326) AS geography)
		        ) AS distancia_metros
		    FROM public.centro_educativo c
		    JOIN public.localidad l ON c.localidad_id = l.id
		    WHERE (:provinciaId IS NULL OR l.provincia_id = :provinciaId)
		      AND (
		            (:latitud IS NULL OR :longitud IS NULL)
		            OR ST_DWithin(
		                c.geom,
		                CAST(ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326) AS geography),
		                :radioKm * 1000
		              )
		          )
		    ORDER BY distancia_metros
		""", nativeQuery = true)
		List<CentroEducativo> buscarConPosicion(
		    @Param("provinciaId") Long provinciaId,
		    @Param("latitud") Double latitud,
		    @Param("longitud") Double longitud,
		    @Param("radioKm") Double radioKm
		);

}
