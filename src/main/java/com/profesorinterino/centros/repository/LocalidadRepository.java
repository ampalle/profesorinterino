package com.profesorinterino.centros.repository;


import com.profesorinterino.centros.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
    List<Localidad> findByProvinciaId(Long provinciaId);
    Optional<Localidad> findByNombreAndProvinciaId(String nombre, Long provinciaId);
}