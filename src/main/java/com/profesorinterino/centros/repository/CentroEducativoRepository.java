package com.profesorinterino.centros.repository;

import com.profesorinterino.centros.model.CentroEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CentroEducativoRepository extends JpaRepository<CentroEducativo, String> {

	@Query("""
		    SELECT c FROM CentroEducativo c
		    WHERE (:nombre IS NULL OR LOWER(c.denominacionEspecifica) LIKE LOWER(CONCAT('%', :nombre, '%')))
		      AND (:provinciaId IS NULL OR c.localidad.provincia.id = :provinciaId)
		      AND (:localidadId IS NULL OR c.localidad.id = :localidadId)
		""")
		List<CentroEducativo> buscar(@Param("nombre") String nombre,
		                             @Param("provinciaId") Long provinciaId,
		                             @Param("localidadId") Long localidadId);
}
