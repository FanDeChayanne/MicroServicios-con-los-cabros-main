package com.MicroServicios.Microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;

@SpringBootApplication
public class MicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosApplication.class, args);
	}

	@Bean
	public OpenAPI customerOpenApi() {
		return new OpenAPI()
		.info(new io.swagger.v3.oas.models.info.Info()
		.title(" Micro Servicios ")
		.version("V1")
		.description("Creacion de microservicios Coef II")
		);
	}
}
