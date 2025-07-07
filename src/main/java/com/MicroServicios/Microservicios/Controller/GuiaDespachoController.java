package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.MicroServicios.Microservicios.Model.GuiaDespacho;
import com.MicroServicios.Microservicios.Model.Transportista;
import com.MicroServicios.Microservicios.Repository.GuiaDespachoRepository;
import com.MicroServicios.Microservicios.Repository.TransportistaRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/guia")

@Tag (name = "Guia de despacho", description = "Administracion de guias de despacho")
public class GuiaDespachoController {

    @Autowired
    private GuiaDespachoRepository guiaRepository;
    @Autowired
    private TransportistaRepository transportistaRepository;

    @PostMapping("/grabar/{id}")
    public GuiaDespacho grabarGuia(@RequestBody GuiaDespacho guia, @PathVariable Long id) {
        Transportista t = transportistaRepository.findById(id).orElse(null);
        guia.setTransportista(t);
        return guiaRepository.save(guia);
    }

    private EntityModel<GuiaDespacho> toModel(GuiaDespacho guia) {
    return EntityModel.of(
        guia
        // No se agregan links porque solo hay un método de grabar
    );
}

}
