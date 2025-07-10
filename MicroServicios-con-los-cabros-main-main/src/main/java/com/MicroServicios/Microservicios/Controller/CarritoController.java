package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Carrito;
import com.MicroServicios.Microservicios.Model.Cliente;
import com.MicroServicios.Microservicios.Repository.CarritoRepository;
import com.MicroServicios.Microservicios.Repository.ClienteRepository;


import org.springframework.hateoas.EntityModel;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // No se usa actualmente

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")

@Tag (name = "Carrito", description = "Administracion de carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/grabar/{id}")
    public Carrito postCarrito(@RequestBody Carrito car, @PathVariable Long id) {
        Cliente cli = clienteRepository.findById(id).orElse(null);
        Carrito c = new Carrito();
        c.setFecha(car.getFecha());
        c.setCliente(cli);
        carritoRepository.save(c);
        return c;
    }

    @PutMapping("/editar/{carritoId}/cliente/{clienteId}")
    public Carrito editarCarrito(
            @RequestBody Carrito car, @PathVariable Long carritoId, @PathVariable Long clienteId) {
        Carrito carritoExistente = carritoRepository.findById(carritoId).orElse(null);
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (carritoExistente == null || cliente == null) {
            return null;
        }

        carritoExistente.setFecha(car.getFecha());
        carritoExistente.setCliente(cliente);

        carritoRepository.save(carritoExistente);
        return carritoExistente;
    }
    @GetMapping("/listar")
    public List<EntityModel<Carrito>> listarCarritos() {
        return carritoRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }
  private EntityModel<Carrito> toModel(Carrito carrito) {
    return EntityModel.of(
        carrito
        // No se agregan links porque no hay métodos de obtener, eliminar ni listar carritos
    );
}
}


