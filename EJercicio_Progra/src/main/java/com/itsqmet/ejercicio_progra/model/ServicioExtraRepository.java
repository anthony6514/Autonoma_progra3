package com.itsqmet.ejercicio_progra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioExtraRepository extends JpaRepository<ServicioExtra, Long> {
}