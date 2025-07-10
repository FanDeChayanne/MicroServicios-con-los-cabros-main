package com.MicroServicios.Microservicios.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    // creacion relacion con cliente 

    @OneToMany(mappedBy = "comuna")
    @JsonIgnoreProperties("comuna")     // evita ciclos desde cliente hacia comuna      

    // creacion ArrayList
    private List<Cliente> clientes = new ArrayList<>();

    
}
