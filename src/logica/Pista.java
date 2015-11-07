package logica;

/**
 * Created by Daniel on 17/10/2015.
 */
public class Pista {

	/**
	 * Descripcion apropiada de la pista para ayudar a resolve el reto
	 */
    private String descripcion;
    
    /**
     * Consecutivo para enumerar las pistas de un reto
     */
    private int consecutivo;

    /**
     * Constructor de la clase
     * @param descripcion
     * @param consecutivo
     */
    public Pista(String descripcion, int consecutivo) {
        this.descripcion = descripcion;
        this.consecutivo = consecutivo;
    }

    
    //Getters and setters
    public String getDescripcion() {
        return descripcion;
    }

    public int getConsecutivo() {
        return consecutivo;
    }
}
