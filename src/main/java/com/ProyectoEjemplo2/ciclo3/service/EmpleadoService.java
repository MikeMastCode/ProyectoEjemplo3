package com.ProyectoEjemplo2.ciclo3.service;

import Modelos.Empleado;
import Modelos.Empresa;
import com.ProyectoEjemplo2.ciclo3.Repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
 @Autowired
 EmpleadoRepository empleadoRepository;
 //Metodo para ver todos los empleados registrados
 public List<Empleado> getAllEmpleado(){
  List<Empleado> empleadoList = new ArrayList<>();
  empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
  return empleadoList;
 }
//MEtodo para buscaro empleados por ID
 public Optional<Empleado> getEmpleadoById(Integer id){//Existe optional y asi se podria usar

     return empleadoRepository.findById(id);
 }
 //MEtodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa (Integer id){
     return empleadoRepository.findByEmpresa(id);
    }

//Metodo para guardar o actualizar registro en Empleados
public boolean saveOrUpdateEmpleado(Empleado empl) {
    Empleado emp = empleadoRepository.save(empl);
    if (empleadoRepository.findById(emp.getId()) != null) {
        return true;
    }
    return false;
}

//Metodo para eliminar un registro de Empleado por ID
    public boolean deleteEmpleado(Integer id){
       empleadoRepository.deleteById(id);
       if(this.empleadoRepository.findById(id).isPresent()){
    return false;
        }
        return true;
    }

}
