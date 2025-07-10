package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {
    
}
