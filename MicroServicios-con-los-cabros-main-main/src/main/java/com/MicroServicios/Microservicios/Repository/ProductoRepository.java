package com.MicroServicios.Microservicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MicroServicios.Microservicios.Model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
