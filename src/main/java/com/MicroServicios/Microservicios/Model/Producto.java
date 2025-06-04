package com.MicroServicios.Microservicios.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String urlImagen;
    private int precio;
    private int stock;
    private String categoria;
    private String fechaCreacion;
    

    // relacion con carrito
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    @JsonIgnoreProperties("productos")

    private Carrito carrito;
}
