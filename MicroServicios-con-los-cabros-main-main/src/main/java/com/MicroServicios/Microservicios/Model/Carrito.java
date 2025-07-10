package com.MicroServicios.Microservicios.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    // Se une con clientes
    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("carrito") // evita ciclos desde carrito hacia cliente

    // OneToOne, no puede ser una lista
    private Cliente cliente;

    // Se une con productos
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("carrito") // evita ciclos desde carrito hacia producto

    private List<Producto> productos = new ArrayList<>();

    // se une con Venta
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Venta> ventas = new ArrayList<>();

}
