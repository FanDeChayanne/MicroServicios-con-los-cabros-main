package com.MicroServicios.Microservicios.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Cliente;
import com.MicroServicios.Microservicios.Model.Comuna;
import com.MicroServicios.Microservicios.Repository.ClienteRepository;
import com.MicroServicios.Microservicios.Repository.ComunaRepository;   

@RestController
@RequestMapping("/api/comuna")
public class ComunaController {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/grabar/{id}")
    public Comuna postComuna(@RequestBody Comuna com, @PathVariable Long id ){
        Cliente cli = clienteRepository.findById(id).orElse(null);
        Comuna c = new Comuna();
        c.setNombre(com.getNombre());
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cli);
        c.setClientes(clientes);
        cli.setComuna(c);
        comunaRepository.save(c);
        return c;
    }

    @GetMapping("/listar")
    public List<Comuna> getComunas() {
        return comunaRepository.findAll();
    } 
    
}
