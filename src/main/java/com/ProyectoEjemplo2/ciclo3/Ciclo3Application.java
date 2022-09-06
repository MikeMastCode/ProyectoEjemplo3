package com.ProyectoEjemplo2.ciclo3;

import Modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello(){

        return "Hola Ciclo 3 bien bien :D";
	}
	@GetMapping("/test")
	public String test() {

		Empresa emp = new Empresa("Solar SAS","Calle 2", "3213213211","80022121");
		emp.setNombre("Solar123");
		return emp.getNombre();
	}
		public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);
	}

}
