package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServicios.Microservicios.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    
}
