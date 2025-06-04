package com.MicroServicios.Microservicios.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String hApertura;
    private String hCierre;
    private String direccion;

    // relacion transportistas
    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transportista> transportistas = new ArrayList<>();    
    

    // relacion con Venta
    @OneToMany(mappedBy = "tienda")
    @JsonIgnoreProperties("tienda")    // evita ciclos desde cliente hacia comuna      

    // creacion ArrayList
    private List<Venta> venta = new ArrayList<>();
    
}

