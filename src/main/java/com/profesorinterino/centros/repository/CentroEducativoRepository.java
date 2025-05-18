package com.profesorinterino.centros.repository;

import com.profesorinterino.centros.model.CentroEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CentroEducativoRepository extends JpaRepository<CentroEducativo, String> {
    List<CentroEducativo> findByLocalidadId(Long localidadId);
}