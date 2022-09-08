package com.ProyectoEjemplo2.ciclo3.Controller;

import Modelos.Empleado;
import Modelos.Empresa;
import com.ProyectoEjemplo2.ciclo3.service.EmpleadoService;
import com.ProyectoEjemplo2.ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;
//EMPRESAS
    @GetMapping({"/", "/verEmpresas"})
    public String viewEmpresas(Model model,@ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emplist", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje) {
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje","saveOk");
            return "redirect:/verEmpresa";

        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/agregarEmpresa";

    }


    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        //Se crea un atributo

        Empresa emp =  empresaService.getEmpresaById(id);
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if( empresaService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje","UpdateOk");
            return "redirect:/VerEmpresas";

        }
        redirectAttributes.addFlashAttribute("mensaje","UpdateError");
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
   if (empresaService.deleteEmpresa(id) ==true)

    {
        redirectAttributes.addFlashAttribute("mensaje", "updateOK");
        return "redirect:/VerEmpresas";
    }
    redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerEmpresas";
      }


    //EMPLEADO

    @GetMapping({"/verEmpleados"})
    public String viewEmpleados(Model model,@ModelAttribute("mensaje") String mensaje) {
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        model.addAttribute("mensaje", mensaje);
        return "verEmpleados"; //Llamamos al HTML
    }
    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje) {
        Empleado emple = new Empleado();
        model.addAttribute("emple", emple);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresas();
        model.addAttribute("emplelist",listaEmpresas);
        return "agregarEmpleado"; //Llamar HTML;
    }
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado empl, RedirectAttributes redirectAttributes) {
        if (empleadoService.saveOrUpdateEmpleado(empl) == true) {
            redirectAttributes.addFlashAttribute("mensaje","saveOk");
            return "redirect:/verEmpleado";

        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpleado";

}
    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        //Se crea un atributo
//Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para
        Empleado empl =  empleadoService.getEmpleadoById(id).get();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje",mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresas();
        model.addAttribute("emplelist",listaEmpresas);
        return "editarEmpleado";
    }
    @PostMapping("/ActualizarEm´leado")
    public String updateEmpleado(Empleado empl, RedirectAttributes redirectAttributes){
        if( empleadoService.saveOrUpdateEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje","UpdateOk");
            return "redirect:/VerEmpleado";

        }
        redirectAttributes.addFlashAttribute("mensaje","UpdateError");
        return "redirect:/EditarEmleado";
    }
    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empleadoService.deleteEmpleado(id) ==true)

        {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpleado";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerEmpleado";
    }
@GetMapping ("/Empresa/{id}/Empleados")//Filtrar los empleados por empresa
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<Empleado> listaEmpleados = empleadoService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist",listaEmpleados);
        return "verEmpleados";// Llamamos al html con emplelist de los empleados filtrados
}


}

