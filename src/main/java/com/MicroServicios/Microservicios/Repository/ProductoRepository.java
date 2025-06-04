package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
}
