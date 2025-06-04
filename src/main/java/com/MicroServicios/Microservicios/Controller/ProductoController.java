package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Carrito;
import com.MicroServicios.Microservicios.Model.Producto;
import com.MicroServicios.Microservicios.Repository.CarritoRepository;
import com.MicroServicios.Microservicios.Repository.ProductoRepository;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CarritoRepository carritoRepository;

    @PostMapping("/grabar/{id}")
    public Producto postProducto(@RequestBody Producto pro, @PathVariable Long id){
        Carrito car = carritoRepository.findById(id).orElse(null);
        Producto p = new Producto();
        p.setNombre(pro.getNombre());
        p.setDescripcion(pro.getDescripcion());
        p.setUrlImagen(pro.getUrlImagen());
        p.setPrecio(pro.getPrecio());
        p.setStock(pro.getStock());
        p.setCategoria(pro.getCategoria());
        p.setFechaCreacion(pro.getFechaCreacion());
        p.setCarrito(car);
        productoRepository.save(p);
        return p;
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteProducto(@PathVariable Long id ) { 
        productoRepository.deleteById(id);
    }    
    
}
