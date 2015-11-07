package logica;

import java.util.ArrayList;

/**
 * Created by Daniel on 17/10/2015.
 */
public class Reto {

	/**
	 * identificador unico del reto
	 */
	private String codigo;
	
	/**
	 * Descripcion apropiada del reto
	 */
	private String descripcion;
	
	/**
	 * coordenada latitud del reto
	 */
	private String latitud;
	
	/**
	 * coordenada longitud del reto
	 */
	private String longitud;
	
	/**
	 * El delta es la diferencia entre una coordenada maxima y minima para crear un cuadrado
	 * que sera el area valida del reto para cumplirse
	 */
	private double delta;

	/**
	 * Contenedor de pistas
	 */
	private ArrayList<Pista> pistas;

	/**
	 * Constructor de la clase para crear un reto
	 * @param codigo
	 * @param descripcion
	 * @param latitud
	 * @param longitud
	 * @param delta
	 */
	public Reto(String codigo, String descripcion, String latitud,
			String longitud, String delta) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		pistas = new ArrayList<Pista>();
		this.latitud = latitud;
		this.longitud = longitud;

		// inicializarCoordenadasReto(latitud,longitud, delta);

	}

	// private void inicializarCoordenadasReto(String latitud, String longitud,
	// String delta){
	// double longitudx = Double.parseDouble(longitud);
	// double latitudy = Double.parseDouble(latitud);
	// double deltaL = Double.parseDouble(delta);
	//
	// deltaL = deltaL* Math.pow(10, -6);
	//
	//
	// latitudMax = latitudy + deltaL;
	// latitudMin = latitudy - deltaL;
	//
	// longitudMax = longitudx + deltaL;
	// longitudMin = longitudx - deltaL;
	// }
	
	/**
	 * Agrega una pista al reto
	 * @param descripcion - descripcion de la pista para resolver el reto
	 */
	public void agregarPista(String descripcion) {
		int consecutivo = pistas.size() + 1;
		Pista pista = new Pista(descripcion, consecutivo);
		pistas.add(pista);
	}

	// Getter and Setters

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getLatitud() {
		return latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public double getDelta() {
		return delta;
	}

	public ArrayList<Pista> getPistas() {
		return pistas;
	}

	/**
	 * Metodo que se encarga de enviar la descripcion de su pista, si el
	 * consecutivo es 1 retorna la pista que se encuentra en la posicion 0
	 * 
	 * @param nConsecutivo
	 * @return Descripcion de la pista
	 */
	public String darDescripcionPista(int nConsecutivo) {
		if (nConsecutivo <= pistas.size()) {
			int consecutivo = nConsecutivo - 1;
			return pistas.get(consecutivo).getDescripcion();
		} else
			return "";
	}

}
