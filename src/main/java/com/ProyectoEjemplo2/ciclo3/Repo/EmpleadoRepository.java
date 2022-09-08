package com.ProyectoEjemplo2.ciclo3.Repo;

import Modelos.Empleado;
import com.ProyectoEjemplo2.ciclo3.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository  extends CrudRepository<Empleado, Integer> {
 @Query(value = "select e from Empleado e where e.empresa = ?1", nativeQuery =true)

 public abstract ArrayList<Empleado> findByEmpresa(Integer id);




}
