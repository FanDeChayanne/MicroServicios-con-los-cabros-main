package com.MicroServicios.Microservicios.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaVenta;
    private Double subTotal;
    private String direccionEnvio;
    private String medioPago;
    private float total;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    @JsonBackReference
    private Carrito carrito;



    // relacion venta tienda
    @ManyToOne
    @JoinColumn(name = "tienda_id")
    @JsonIgnoreProperties("venta") //evita el ciclo y permite ver la comuna en listar
    private Tienda tienda;


    // relacion Venta Reclamacion
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reclamacion> reclamaciones = new ArrayList<>();


}
