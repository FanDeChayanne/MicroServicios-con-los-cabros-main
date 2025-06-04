package com.MicroServicios.Microservicios;

import com.MicroServicios.Microservicios.Controller.TiendaController;
import com.MicroServicios.Microservicios.Model.Tienda;
import com.MicroServicios.Microservicios.Service.TiendaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// WebMvcTest es un test unitario para el controlador, este solo se enfoca en la capa web
// Es decir, es solo una simulacion, no carga bd 

// Esta linea especifica que solo testeara TiendaController
@WebMvcTest(TiendaController.class)
public class TiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TiendaService tiendaService;

    @Test
    public void testGetAllTiendas() throws Exception {

        // se crean 2 tiendas con valores fijos
        Tienda tienda1 = new Tienda(1L, "Falabella", "Calle A", null);
        Tienda tienda2 = new Tienda(2L, "Ripley", "Calle B", null);


        // LOGICA:
        // cuando se llame a "tiendaService.getAllTiendas()", se devuelve la lista con esas dos tiendas
        when(tiendaService.getAllTiendas()).thenReturn(Arrays.asList(tienda1, tienda2));

        // Simula "HTTPS Get" hacia el endpoint
        mockMvc.perform(get("/tiendas"))

                // se espera que el HTTPS sea 200 (ok)
                .andExpect(status().isOk())

                // se espera que el Json devuelto tenga un tamaño de 2
                .andExpect(jsonPath("$.size()").value(2))

                // se espera que el primer elemento tenga el nombre "Falabella"
                .andExpect(jsonPath("$[0].nombre").value("Falabella"))

                // se espera que el segundo elemento tenga el nombre "Ripley"
                .andExpect(jsonPath("$[1].nombre").value("Ripley"));
    }
}
