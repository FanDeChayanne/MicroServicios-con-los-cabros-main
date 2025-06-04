package com.MicroServicios.Microservicios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Transportista;
import com.MicroServicios.Microservicios.Model.Vehiculo;
import com.MicroServicios.Microservicios.Repository.TransportistaRepository;
import com.MicroServicios.Microservicios.Repository.VehiculoRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/vehiculo")

@Tag (name = "Vehiculos", description = "Administracion de Vehiculos")
public class VehiculoController {

    @Autowired
    private TransportistaRepository transportistaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;


    @PostMapping("/grabar/{id}")
    public Transportista postVehiculo(@RequestBody Vehiculo veh,  @PathVariable Long id){
        Transportista tra = transportistaRepository.findById(id).orElse(null);
        Vehiculo v = new Vehiculo();
        v.setMarca(veh.getMarca());
        v.setPatente(veh.getPatente());
        v.setColor(veh.getColor());
        v.setModelo(veh.getModelo());
        v.setKilometraje(veh.getKilometraje());
        // guarda y luego asigna el vehiculo
        vehiculoRepository.save(v);
        tra.setVehiculo(v);
        return transportistaRepository.save(tra);
    }
    
}
