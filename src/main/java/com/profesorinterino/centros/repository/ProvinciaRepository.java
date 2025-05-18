package com.profesorinterino.centros.repository;

import com.profesorinterino.centros.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    List<Provincia> findByComunidadAutonomaId(Long comunidadAutonomaId);
    Optional<Provincia> findByNombreAndComunidadAutonomaId(String nombre, Long comunidadAutonomaId);
}