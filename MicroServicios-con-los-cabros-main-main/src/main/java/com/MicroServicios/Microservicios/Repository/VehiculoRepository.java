package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    
}
