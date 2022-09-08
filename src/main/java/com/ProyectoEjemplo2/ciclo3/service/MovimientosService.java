package com.ProyectoEjemplo2.ciclo3.service;

import Modelos.Empresa;
import Modelos.MovimientoDinero;
import com.ProyectoEjemplo2.ciclo3.Repo.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosService {
    @Autowired
    MovimientosRepository movimientosRepository;

    public List<MovimientoDinero> getAllMovimientos() { //metodo que me muestra todos los movimientos sin ningun filtro
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id) {// ver movimientos en pantalla
        return movimientosRepository.findById(id).get();

    }

    public MovimientoDinero saveOrUpdateMovimiento(MovimientoDinero movimientoDinero) {//Guardar o actualizar
        MovimientoDinero mov = movimientosRepository.save(movimientoDinero);
        return mov;
    }

    public boolean deleteMovimiento(Integer id) {//Elimina movimiento por id
        movimientosRepository.deleteById(id);//Eliminar usando el metodo que nos ofrece el repositorio
        if (this.movimientosRepository.findById(id).isPresent()) { //Si al buscar el movimiento lo encontramos no se elimino (false)
            return false;
        }
        return true;// Si al buscar el movimiento no lo encontramos si lo elimino true
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) {//Obtener teniendo en cuenta el id del empleado
        return this.movimientosRepository.findByEmpleado(id);

    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) { //Obtener moviientos teniendo en cuenta el id de la empresa a la cual pertenecen los empleados que la registraron
        return movimientosRepository.findByEmpresa(id);
    }
}
