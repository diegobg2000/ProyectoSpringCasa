package com.diegoCasa.iniciandoSpring2.negocio.rutas;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.diegoCasa.iniciandoSpring2.beans.CocheBean;
import com.diegoCasa.iniciandoSpring2.beans.ListaCoches;

@Controller
public class RutasBasicas {

	@GetMapping("/")
	public String inicial() {
		
		return "index";
	}
	
	@GetMapping("/mostrar")
	public String mostrar(Model model) {
		CocheBean coche1 = new CocheBean();
		coche1.setMarca("Bugati");
		coche1.setModelo("GT");
		coche1.setColor("Azul");
		coche1.setCaballos(400);
		
		/*Lo primero es el nombre con el que va  aidentificar el html al objeto y el segundo parametro es el objeto
		 */
		model.addAttribute("coche", coche1);
		return "mostrar";
	}
	
	@GetMapping("/lista")
	/*Siempre que necesitamos sacar dartos del modelo utilizamos Model*/
	public String lista(Model model) {
		/*En esta lista acabamos de traer los datos del Singleton
		 * Cada vez que lo utilizamos en otras clases va a ser el mismo objeto*/
		List<CocheBean> listacoche = ListaCoches.getListaCoches();
		model.addAttribute("lista", listacoche);
		if(listacoche == null) {
			System.out.println("LA lista esta vacia");
		}else{
			System.out.println("Lsita llena");
		}
		return "lista";
		
	}
	
	@GetMapping("/addCoche")
    public String addCoche(Model model) {
        model.addAttribute("coche", new CocheBean());
        return "addCoche";
    }
	
	//Metodo Post --> Ruta para el submit
    @PostMapping("/formulario")
    public String formulario(@ModelAttribute CocheBean coche) {/*Este coche que nos entra se lo pasamos al CRUD
    El @ModelAttrbute --> Indica que el parametro formal de entrada viene de un html*/
        ListaCoches.addCoche(coche);
        return"redirect:/mostrarCoche"; //Esto es una ruta, es la forma de redirigir
    }
    
    @GetMapping("/mostrarCoche")
    /*Porque vamos a necesitar mostrar datos Model model, va siempre con los POST
     * Mientras que el @ModelAtribute va siempre con los GET*/
    public String mostrarCoche(Model model){
    model.addAttribute("coches", ListaCoches.readCoche());
    
    	return "mostrarCoche";
    }
}
