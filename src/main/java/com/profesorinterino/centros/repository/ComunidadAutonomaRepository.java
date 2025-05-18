package com.profesorinterino.centros.repository;

import com.profesorinterino.centros.model.ComunidadAutonoma;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunidadAutonomaRepository extends JpaRepository<ComunidadAutonoma, Long> {
	Optional<ComunidadAutonoma> findByNombre(String nombre);
}