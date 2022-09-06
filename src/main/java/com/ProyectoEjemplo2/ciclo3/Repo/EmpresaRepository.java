package com.ProyectoEjemplo2.ciclo3.Repo;

import Modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Anotacion que le dice al spring que esta clase es un repositorio
public interface EmpresaRepository extends JpaRepository <Empresa, Integer>{

}
