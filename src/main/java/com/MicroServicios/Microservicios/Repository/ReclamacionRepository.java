package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Reclamacion;

public interface ReclamacionRepository extends JpaRepository<Reclamacion,Long> {
    
}
