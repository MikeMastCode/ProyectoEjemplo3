package com.ProyectoEjemplo2.ciclo3.Controller;

import Modelos.Empresa;
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
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes)
    {try{empresaService.deleteEmpresa(id);
        redirectAttributes.addFlashAttribute("mensaje","updateOK");
        return "redirect:/VerEmpresas";
    }catch (Exception e){redirectAttributes.addFlashAttribute("mensaje","deleteError");
            return "redirect:/VerEmpresas";
    }
 }

}