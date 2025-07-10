package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MicroServicios.Microservicios.Model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
