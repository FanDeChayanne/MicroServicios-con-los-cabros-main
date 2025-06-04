package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna,Long> {
    
}
