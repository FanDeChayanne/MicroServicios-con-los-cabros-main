package com.MicroServicios.Microservicios.Controller;

import com.MicroServicios.Microservicios.Model.Tienda;
import com.MicroServicios.Microservicios.Repository.TiendaRepository;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")

@Tag (name = "Tiendas", description = "Administracion de tiendas")
public class TiendaController {

    @Autowired
    private TiendaRepository tiendaRepository;


    @PostMapping("/grabar")
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    // "->" Expresion lambda (permite representar funciones anonimas)

    @PutMapping("/modificar/{id}")
    public Tienda putTienda(@RequestBody Tienda tie, @PathVariable Long id){
        return tiendaRepository.findById(id).map(tienda ->{
            tienda.setHApertura(tie.getHApertura());
            tienda.setHCierre(tie.getHCierre());
            return tiendaRepository.save(tienda);
        }).orElseThrow(() -> new RuntimeException("No se encuentra una tienda con el id: "+ id));
    }
    
    @GetMapping("/listar")
    public List<EntityModel<Tienda>> listarTiendas() {
        return tiendaRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    private EntityModel<Tienda> toModel(Tienda tienda) {
        return EntityModel.of(
            tienda,
            linkTo(methodOn(TiendaController.class).putTienda(tienda, tienda.getId())).withRel("modificar"),
            linkTo(methodOn(TiendaController.class).listarTiendas()).withRel("listar")
        );
    }
}
