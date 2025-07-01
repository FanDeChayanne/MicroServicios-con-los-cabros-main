package com.MicroServicios.Microservicios.TestJUnit;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.MicroServicios.Microservicios.Model.Cliente;
import com.MicroServicios.Microservicios.Repository.ClienteRepository;





// evitar el cambio de datos de las tablas (JUNIT)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        cli.setActivo(false);

        clienterepository.save(cli);
    }

    @Test
    void testListar() {
        List<Cliente> lista = clienterepository.findAll();
        boolean encontro = lista.stream().anyMatch(u->"Joao".equals(u.getNombre()));
        assert(encontro);
    }
    // antes de testear, generar en la BD real un cliente con id y nombre correspondiente para verificar 
    @Test
    void testEliminar(){
        Cliente cli = clienterepository.findById(2L).orElse(null);
        assert(cli!=null);
        if (cli!=null) {
            clienterepository.delete(cli);
        } 
    }
}