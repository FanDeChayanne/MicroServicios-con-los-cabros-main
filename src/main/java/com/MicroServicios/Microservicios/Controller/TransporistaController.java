package com.MicroServicios.Microservicios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Tienda;
import com.MicroServicios.Microservicios.Model.Transportista;
import com.MicroServicios.Microservicios.Repository.TiendaRepository;
import com.MicroServicios.Microservicios.Repository.TransportistaRepository;


import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/transportista")

@Tag (name = "Transportistas", description = "Administracion de transportistas")
public class TransporistaController {
    
    @Autowired
    private TransportistaRepository transportistaRepository;
    @Autowired
    private TiendaRepository tiendaRepository;
    
    @GetMapping("/listar")
    public List<EntityModel<Transportista>> getTransportistas() {
        return transportistaRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }
    

    @PostMapping("/grabar/{id}")
    public Transportista postTransportista(@RequestBody Transportista tra, @PathVariable Long id) {
        Tienda tienda = tiendaRepository.findById(id).orElse(null);
        tra.setTienda(tienda);
        return transportistaRepository.save(tra);
    } 

    private EntityModel<Transportista> toModel(Transportista transportista) {
    return EntityModel.of(
        transportista,
        linkTo(methodOn(TransporistaController.class).getTransportistas()).withRel("listar")
    );
}
}
