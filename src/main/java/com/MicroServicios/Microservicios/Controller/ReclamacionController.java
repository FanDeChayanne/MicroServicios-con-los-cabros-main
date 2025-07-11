package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MicroServicios.Microservicios.Model.Reclamacion;
import com.MicroServicios.Microservicios.Repository.ReclamacionRepository;


import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/reclamaciones")

@Tag (name = "Reclamaciones", description = "Administracion de reclamaciones")
public class ReclamacionController {

    @Autowired
    private ReclamacionRepository reclamacionRepository;

    // Crear reclamación
    @PostMapping
    public Reclamacion postReclamacion(@RequestBody Reclamacion recl) {
        return reclamacionRepository.save(recl);
    }

    // Listar todas las reclamaciones
    @GetMapping
    public List<Reclamacion> listarReclamaciones() {
        return reclamacionRepository.findAll();
    }

    // Actualizar reclamación
    @PutMapping("/{id}")
    public Reclamacion putReclamacion(@RequestBody Reclamacion Recl, @PathVariable Long id) {
        return reclamacionRepository.findById(id).map(reclamacion -> {
            reclamacion.setTipo(Recl.getTipo());
            reclamacion.setDescripcion(Recl.getDescripcion());
            reclamacion.setFechaReclamacion(Recl.getFechaReclamacion());
            reclamacion.setEstado(Recl.isEstado());
            reclamacion.setSolucion(Recl.getSolucion());
            reclamacion.setFechaResolucion(Recl.getFechaResolucion());
            return reclamacionRepository.save(reclamacion);
        }).orElseThrow(() -> new RuntimeException("No se encuentra una Reclamacion con el id: "+ id));
    }

    // Eliminar reclamacion
    @DeleteMapping("/{id}")
    public void deleteReclamacion(@PathVariable Long id) {
        reclamacionRepository.deleteById(id);
    }
    
    // Listar reclamaciones usando HATEOAS con EntityModel
    @GetMapping("/listar")
    public List<EntityModel<Reclamacion>> listarReclamacionesHateoas() {
        return reclamacionRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }
    
    private EntityModel<Reclamacion> toModel(Reclamacion reclamacion) {
        return EntityModel.of(
            reclamacion,
            linkTo(methodOn(ReclamacionController.class).putReclamacion(reclamacion, reclamacion.getId())).withRel("modificar"),
            // No se agrega link de borrar porque deleteReclamacion es void y no es compatible con HATEOAS
            linkTo(methodOn(ReclamacionController.class).listarReclamaciones()).withRel("listar")
        );
    }
}
