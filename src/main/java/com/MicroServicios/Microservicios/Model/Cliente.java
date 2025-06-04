package com.MicroServicios.Microservicios.Model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

//Pertenecen a Jakarta
// Indica que la clase representa una tabla en la BD
@Entity

// Genera automaticamente los Getter, Setters, toString, equals, hashCode y constructores
@Data
//--------------------

@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Rut;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String telefono;
    private int edad;
    private String direccion;  // comuna se hereda por relaciones
    private String fechaRegistro;
    private boolean activo;
    // Id de comuna se hereda por las relaciones
    

    // se une con Comuna
    @ManyToOne
    @JoinColumn(name = "comuna_id")
    @JsonIgnoreProperties("clientes") //evita el ciclo y me permite ver la comuna en listar
    private Comuna comuna;

    // se une con carrito

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cliente")
    private Carrito carrito;




    /* 
    
    Dependencias y sus funciones


    1-Spring boot starter data JPA:

    Permite la interaccion con bases de datos relacionales mediante entidades y repositorios
    (proporciona integracion con hibernate)


    2-Jackson Databind:

    Permite convertir objetos java a JSON y viceversa
    

    3-JDBC

    Soporte para trabajar con JDBC puro (Consultas SQL directas)
    

    4-Spring boot starter web

    Permite crear aplicaciones web y RESTful APIs, Incluye Spring MVC, Tomcat embebido, Jackson y validaciones


    5-Spring Boot DevTools

    Recarga automatica del servidor al guardar archivos, reincio rapido y cacheo inteligente


    6-MySQL Connector

    Controlador JDBC para conexion con base de datos MySQL


    7-Lombok

    Reduce el codigo repetitivo en java, usando anotaciones como    @Getter @Setter @Data

    8- Spring Boot Starter Test

    Incluye herramientas para utilizar pruebas unitarias e integradas , utiliza JUnit, Mockito, AssertJ,
    Spring Test


--------------------------------------------------------
    Hecho:

    comuna: id nombre 
    
    cliente: id rut nombre apellido edad comuna_id direccion:

    carrito: id fecha

    producto:  id, nombre ,desc ,img, precio, dcto  FALTA APLICAR DCTO 

    vehiculo: id marca patente color modelo km

    añadir horarios a los trabajadores

    transportista: id nombre apellido celular direccion vehiculo_id

    venta : id fecha_venta carrito_id total medio_pago

    guia_despacho: id fecha_envio fecha_recep estado receptor transportista celular_cont fotos

    

    */
}
   
