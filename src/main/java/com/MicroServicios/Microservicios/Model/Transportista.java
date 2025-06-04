package com.MicroServicios.Microservicios.Model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String celular;
    private String direccion;


    // relacion con vehiculo
    @OneToOne
    @JoinColumn(name = "vehiculo_id")  
    @JsonManagedReference
    
    private Vehiculo vehiculo;
    
    // relacion con tienda
    @ManyToOne
    @JoinColumn(name = "tienda_id")
    @JsonBackReference
    private Tienda tienda;
   

    // relacion con Guia Despacho
    @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GuiaDespacho> guiasDespacho = new ArrayList<>();

}
