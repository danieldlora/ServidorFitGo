package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author Daniel Clase Controladora de todo el conjunto de Retos y Pistas
 */
public class Retos {

	/**
	 * Estructura encargada de contener todos los retos del sistema
	 */
	private HashMap<String, Reto> retos;

	public Retos() {
		retos = new HashMap<String, Reto>();
	}

	/**
	 * Metodo encargado de agregar un reto a la coleccion de retos.
	 * 
	 * @param codigo
	 * @param descripcion
	 * @param latitud
	 * @param longitud
	 * @param delta
	 */
	public void agregarReto(String codigo, String descripcion, String latitud,
			String longitud, String delta) {
		Reto reto = new Reto(codigo, descripcion, latitud, longitud, delta);

		retos.put(codigo, reto);

	}
	/**
	 * Metodo que se encarga de dar un reto aleatorio diferente a los que se encuentran
	 * concatenados
	 * 
	 * @param concatenacionRetos- codigos de retos concatenados por una "-"
	 * @return Reto
	 */
	public Reto darRetoAleatorio(String concatenacionRetos){
		
		String [] split = concatenacionRetos.split("-");
		Random rnd = new Random();
		boolean nuevoReto = false;
		ArrayList<Reto> as = new ArrayList<Reto>(retos.values());

		Reto reto = null;
		
		while(!nuevoReto){
			int a = (int)(rnd.nextDouble() * retos.size());
			reto = as.get(a);
			nuevoReto = verificarReto(split, reto.getCodigo());
		}
		
		return reto;
	}
	
	/**
	 *  
	 * @param codigos - Contenedora de Codigos
	 * @param codigo - Codigo a buscar
	 * @return boolean que verifica si se encuentra o no el codigo en la coleccion
	 */
	private boolean verificarReto(String[] codigos, String codigo){
		
		boolean encontro = false;
		
		for(int i = 0; i< codigos.length && !encontro; i++){
			if(codigo.equals(codigos[i]))
				encontro = true;
		}
		
		return encontro;
	}
	
	/**
	 * retorna un Reto por su codigo
	 * @param codigo
	 * @return Reto
	 */
	public Reto darReto(String codigo){
		return retos.get(codigo);
	}
	
}
