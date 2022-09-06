package com.ProyectoEjemplo2.ciclo3.service;

import Modelos.Empresa;
import com.ProyectoEjemplo2.ciclo3.Repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//Le decimos a sprigque esta clase es de servicios
@Service
public class EmpresaService {
    @Autowired// conectamos esta clase con el repository de Empresa
    EmpresaRepository empresaRepository; //Creamos un objteto de tipo Empresa Repositoru para poder usar los metodos que dicha clasehereda

    //Metodo que retornara la lista de empresas usando metodos heredados del jpaRepository
    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));//Recorremos el iterable que regresa el metodo findAll del Jpa  y los guardamos e listas
        return empresaList;

    }

    //Metodo que me trae un objeto de tipo Empresas cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id) {
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa

    public Empresa saveOrUpdateEmpresa(Empresa empresa) {
        Empresa emp = empresaRepository.save(empresa);
      return emp;
    }

    //metodo para eliminar empresas registradas
    public boolean deleteEmpresa(Integer id) {
empresaRepository.deleteById(id);//Eliminacion
if (empresaRepository.findById(id)==null){ //Verificacion servicio eliminacion

    return true;
}
return false;
    }
}