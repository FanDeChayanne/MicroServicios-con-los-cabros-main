package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MicroServicios.Microservicios.Model.Venta;
import com.MicroServicios.Microservicios.Model.Carrito;
import com.MicroServicios.Microservicios.Repository.VentaRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.MicroServicios.Microservicios.Repository.CarritoRepository;

@RestController
@RequestMapping("/api/venta")

@Tag (name = "Ventas", description = "Administracion de ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private CarritoRepository carritoRepository;

    @PostMapping("/grabar/{id}")
    public Venta grabarVenta(@RequestBody Venta venta, @PathVariable Long id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        venta.setCarrito(carrito);
        return ventaRepository.save(venta);
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteVenta(@PathVariable Long id){
        ventaRepository.deleteById(id);
    }

    private EntityModel<Venta> toModel(Venta venta) {
    return EntityModel.of(
        venta,
        linkTo(methodOn(VentaController.class).deleteVenta(venta.getId())).withRel("borrar")
    );
}

}
