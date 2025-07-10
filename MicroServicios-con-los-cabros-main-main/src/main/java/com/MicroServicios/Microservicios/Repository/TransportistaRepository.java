package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista,Long> {
    
}
