package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda,Long> {
    
}
