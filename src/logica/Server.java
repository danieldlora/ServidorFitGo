package logica;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase implementa el servidor de fitgo. <br>
 * Se encarga de manejar las conexiones al programa servidor
 */
public class Server {
	
	public final static int PUERTO = 9090;
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es el canal por el que escucha las solicitudes de conexi�n
	 */
	private ServerSocket socket;
	
	/**
	 * Contenedora de todos los retos
	 */
	private Retos retos;

	/**
	 * contenedora de todos los usuarios
	 */
	private Usuarios usuarios;
	
	/**
	 * contenedora de todos los participantes activos
	 */
	private Participantes participantes;
	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el servidor de fitgo
	 * 
	 * @param archivo
	 *            El archivo de propiedades que contiene la configuraci�n para
	 *            el almac�n
	 * @throws Exception
	 *             Se lanza esta excepci�n si hay problemas con el archivo de
	 *             propiedades o hay problemas en la conexi�n a la base de datos
	 * @throws SQLException
	 *             Se lanza esta excepci�n si hay problemas conectando el
	 *             almac�n a la base de datos.
	 */
	public Server() throws SQLException, Exception {
		retos = new Retos();
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * 
	 * Se encarga de dar la instancia de la clase Retos.
	 * 
	 * @return retos- Contenedora de Retos
	 */
	public Retos darRetos(){
		return retos;
	}
	

	/**
	 * Este m�todo se encarga de recibir todas las conexiones entrantes al
	 * servidor
	 */
	public void recibirConexiones() {
		int puerto = PUERTO;
		try {
			socket = new ServerSocket(puerto);
			while (true) {
				// Esperar una nueva conexi�n
				Socket socketCliente = socket.accept();
				PrintWriter out = new PrintWriter(
						socketCliente.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socketCliente.getInputStream()));
				// Crea e inicia un nuevo hilo de ejecuci�n para atender la
				// conexi�n
				ManejadorEvento manejador = new ManejadorEvento(in, out, retos,usuarios,participantes);
				manejador.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			Server servidor = new Server();
			servidor.recibirConexiones();
		}catch(Exception e){
			
		}
	}
}
