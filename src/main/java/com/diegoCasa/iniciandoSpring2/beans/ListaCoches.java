package com.diegoCasa.iniciandoSpring2.beans;

import java.util.ArrayList;
import java.util.List;

/*ESTA CLASE ES UN SIGLETON IMPURO*/
/*El Singleton sirve para imitar una espcie de Base de Datos en java*/
public class ListaCoches {
	/*Creamos una lista*/
	private static List<CocheBean> listacoches = null;
	/*Ponemos el contructor en private para que no puedas crear un objeto desde otra clase*/
	private ListaCoches() {
		List lista = new ArrayList <CocheBean>();
        construir(lista);
        ListaCoches.listacoches = lista;

	}
	
	/*No es un getter pero funciona como un getter --> Queremos que nos de datos una vez, es 
	 * una mezcla entre Getter y el constructor*/
	public static List<CocheBean> getListaCoches(){
		
		/*Se comprueba si la lista esta vacia*/
		if(listacoches == null) {
			
			new ListaCoches();
		}
		return listacoches;
		
	}
	/*Creamos otro metodo */
	public  List<CocheBean> construir(List<CocheBean> lista){
		CocheBean coche1 = new CocheBean();
		coche1.setCaballos(300);
		coche1.setColor("Rojo");
		coche1.setMarca("BMW");
		coche1.setModelo("X6");
		
		CocheBean coche2 = new CocheBean();
		coche2.setCaballos(200);
		coche2.setColor("Negro");
		coche2.setMarca("Mercedes-Benz");
		coche2.setModelo("AMG G-Class");
		
		CocheBean coche3 = new CocheBean();
		coche3.setCaballos(180);
		coche3.setColor("Amarillo");
		coche3.setMarca("Acura");
		coche3.setModelo("R12");
		
		lista.add(coche1);
		lista.add(coche2);
		lista.add(coche3);
		return lista;
	}
	
	public static void addCo(CocheBean coche){
		List listaCo = new ArrayList<CocheBean>();
        listaCo.add(coche);
        ListaCoches.listacoches = listaCo;
        System.out.println("*************************" + listaCo);
        
	}
	/*CRUD*/
    /*
     * C Create @PostMapping recibe la lista de coches esa lista va añadirla al singelton (BBDD)
     * R Read MEtodo para mostrar el singelton
     * U Update metodo para mostrar un formulario y un post para recoger los datos y añadirlos al singelton
     * D Delete metodo que recoje un coche y lo borra en el singelton
     * *
     /


    /*Create*/
	
	/*
    public static void addCoche(CocheBean coche) {
        coche.setId(listacoches.size()+1);
        listacoches.add(coche);
    }
    */
    
    public static void addCoche(CocheBean coche) {
        coche.setId(1);
        System.out.println(coche.getId());
        System.out.println(coche.getMarca());
        System.out.println(coche.getModelo());
        System.out.println(coche.getColor());
        System.out.println(coche.getCaballos());
        ListaCoches.addCo(coche);

    }

    /*Read Lista*/

    public static List <CocheBean> readCoche(){
        return listacoches;
        

    }
    
    /*Read individual*/
    public static CocheBean coche(Integer id) {
        boolean encontrado = false;
        int posicion = 0;

        while(!encontrado && posicion<listacoches.size()) {
            if(listacoches.get(posicion).getId() == id) {
                encontrado = true;
            }
            else {
                posicion++;
            }
        }

        return listacoches.get(posicion);
    }

    /*Update*/
    public void updateCoche(CocheBean coche) {
        int posicion = coche.getId();
        listacoches.set(posicion, coche);

    }

    /*Delete*/
    public CocheBean delCoche(Integer id) {
        boolean encontrado = false;
        int posicion = 0;

        while(!encontrado && posicion<listacoches.size()) {
            if(listacoches.get(posicion).getId() == id) {
                encontrado = true;
            }
            else {
                posicion++;
            }
        }
        return listacoches.get(posicion);

    }
    
}


