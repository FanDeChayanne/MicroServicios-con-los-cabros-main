package com.MicroServicios.Microservicios.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.MicroServicios.Microservicios.Model.Cliente;
import com.MicroServicios.Microservicios.Repository.ClienteRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


//@PathVariable extrae valores de la URL y asigna a variables del metodo

//Es una combinacion de @Controller y  @ResponseBody, se utiliza para crear APIs REST
@RestController
//Define la ruta base o especifica para un controlador o metodo 
@RequestMapping("/api/cliente")

@Tag (name = "Clientes", description = "Administracion de usuarios")
public class ClienteController {

    //Le dice a spring que inyecte automaticamente una instancia de una clase(repositorio)
    @Autowired
    private ClienteRepository clienteRepository;

    // Grabar clientes   
    @PostMapping("/grabar")
    //@RequestBody Indica que el parametro debe ser extraido del cuerpo de la peticion HTTP
    public Cliente postCliente(@RequestBody Cliente cli) {
        return clienteRepository.save(cli);
    }

    //Modificar Cliente por id
    @PutMapping("/modificar/{id}")
    public Cliente putCliente(@RequestBody Cliente cli, @PathVariable Long id){
        return clienteRepository.findById(id).map(cliente ->{
            cliente.setNombre(cli.getNombre());
            cliente.setApellido(cli.getApellido());
            cliente.setEmail(cli.getEmail());
            cliente.setContraseña(cli.getContraseña());
            cliente.setTelefono(cli.getTelefono());
            cliente.setEdad(cli.getEdad());
            cliente.setDireccion(cli.getDireccion());
            cliente.setFechaRegistro(cli.getFechaRegistro());
            cliente.setActivo(cli.isActivo());
            return clienteRepository.save(cli);
        }).orElseThrow(() -> new RuntimeException("No se encuentra un Cliente con el id: "+ id));
    }
    

    // eliminar cliente por id
    @DeleteMapping("/borrar/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
    

    // Listar Clientes
    @GetMapping("/listar")
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
    private EntityModel<Cliente> toModel(Cliente cliente) {
    return EntityModel.of(
        cliente,
        linkTo(methodOn(ClienteController.class).obtenerClientePorId(cliente.getId())).withSelfRel(),
        linkTo(methodOn(ClienteController.class).actualizarCliente(cliente.getId(), cliente)).withRel("actualizar"),
        linkTo(methodOn(ClienteController.class).eliminarCliente(cliente.getId())).withRel("eliminar"),
        linkTo(methodOn(ClienteController.class).listarClientes()).withRel("clientes")
    );
}
    
}
