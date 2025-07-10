package com.MicroServicios.Microservicios.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuiaDespacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaEnvio;
    private String fechaRecep;
    private String estado;
    private String receptor;
    private String celularCont;
    private String urlFoto; 

    @ManyToOne
    @JoinColumn(name = "transportista_id")
    @JsonBackReference
    private Transportista transportista;
}
