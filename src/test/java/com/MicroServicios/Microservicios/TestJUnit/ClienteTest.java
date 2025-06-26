package com.MicroServicios.Microservicios.TestJUnit;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

// evitar el cambio de datos de las tablas (JUNIT)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.replace.NONE)
public class ClienteTest{

    @Autowired
    private ClienteRepository clienterepository;

    //CURD EN JUNIT
    @Test
    void testGrabar(){
        Cliente cli = new Cliente();
        cli.setRut("123");
        cli.setNombre("Pablo");
        cli.setApellido("Castillo");
        cli.setEmail("wawawa123@gmail.com");
        cli.setContraseña("1234567890");
        cli.setTelefono("9 7423 1252");
        cli.setEdad(23);
        cli.setDireccion("Puente Gabriela Carrero 8");
        cli.setFechaRegistro("20-05-2024");
        cli.setActive(false);

        Clienterepository.save(cli);
    }

    @Test
    void testListar() {
        List<Cliente> lista = clienterepository.findAll();
        boolean encontro = lista.stream().anyMatch(u->"Joao".equals(u.getNombre()));
        assert(encontro);
    }

    @Test
    void testEliminar(){
        Cliente cli = clienterepository.findById(2L).orElse();
        assert(cli!=null);
        if (cli!=null) {
            clienterepository.delete(cli);
        } 
    }
}