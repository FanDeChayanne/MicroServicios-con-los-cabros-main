package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.MicroServicios.Microservicios.Model.GuiaDespacho;
import com.MicroServicios.Microservicios.Model.Transportista;
import com.MicroServicios.Microservicios.Repository.GuiaDespachoRepository;
import com.MicroServicios.Microservicios.Repository.TransportistaRepository;

@RestController
@RequestMapping("/api/guia")
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

}
