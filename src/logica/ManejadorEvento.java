package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ManejadorEvento extends Thread {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Comando para desconectar un cliente
	 */
	public final static String DESCONECTAR = "desconectar";

	/**
	 * Comando para verificar la posicion del reto
	 */
	public final static String VERIFICAR_RETO = "verificar reto";

	/**
	 * comando para pedir una pista del reto solicitado
	 */
	public final static String PEDIR_PISTA = "pedir pista";

	/**
	 * comando para iniciar el juego, el cliente solicita un reto
	 */
	public final static String PEDIR_RETO = "pedir reto";

	/**
	 * Indica un error en la ejecución de un comando
	 */
	public final static String RESULTADO_ERROR = "Error";

	/**
	 * Indica un resultado exitoso en la ejecución de un comando
	 */
	public final static String RESULTADO_OK = "Ok";

	/**
	 * Indica que un cliente desea registrarse
	 */
	public final static String REGISTRARSE = "Registrarse";

	/**
	 * indica que un cliente desea conectarse
	 */
	public final static String CONECTARSE = "Conectarse";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * El flujo de salida asociado con el canal de comunicación
	 */
	private PrintWriter out;

	/**
	 * El flujo de entrada asociado con el canal de comunicación
	 */
	private BufferedReader in;

	/**
	 * Asociación Retos, responsable de manejar todos los retos.
	 */
	private Retos retos;

	/**
	 * Asociacion usuarios, responsable de crear o verificar usuarios
	 */
	private Usuarios usuarios;

	/**
	 * asociacion Participantes, responsable de mirar cuantos usuarios estan
	 * conectados y jugando
	 */
	private Participantes participantes;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Método constructor de la clase
	 * 
	 * @param in2
	 *            Es el flujo de entrada asociado con el canal de comunicación
	 * @param out2
	 *            Es el flujo de salida asociado con el canal de comunicación
	 * @param nRetos
	 *            Es la contenedora de Retos
	 */
	public ManejadorEvento(BufferedReader in2, PrintWriter out2, Retos nRetos,
			Usuarios u, Participantes p) {
		in = in2;
		out = out2;
		retos = nRetos;
		usuarios = u;
		participantes = p;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que se ejecuta cuando se quiere comenzar el hilo de ejecución
	 */
	public void run() {
		try {
			recibirComandos();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método se encarga de recibir comandos enviados desde la app movil
	 * del cliente
	 * 
	 * @throws IOException
	 *             Se lanza esta excepción si hay problemas con la comunicación
	 */
	private void recibirComandos() throws IOException {
		boolean conexionTerminada = false;
		while (!conexionTerminada) {
			// Leer un comando
			String comando = in.readLine();

			// Comando para desconectar al cliente
			if (ManejadorEvento.DESCONECTAR.equals(comando)) {
				out.println(ManejadorEvento.RESULTADO_OK);
				conexionTerminada = true;
			}

			else if (ManejadorEvento.CONECTARSE.equals(comando)) {
				conectarse();
			} else if (ManejadorEvento.REGISTRARSE.equals(comando)) {
				registrarse();
			}

			else if (ManejadorEvento.PEDIR_RETO.equals(comando)) {
				darReto();
			}

			else if (ManejadorEvento.PEDIR_PISTA.equals(comando)) {
				darPista();
			}
			// Comando no reconocido
			else {
				out.println(ManejadorEvento.RESULTADO_ERROR);
				out.println("Comando no reconocido: " + comando);
			}
		}
	}

	public void registrarse() {
		try {
			String usuario = in.readLine();
			String password = in.readLine();

			boolean registro = usuarios.registrarUsuario(usuario, password);

			if (registro) {
				out.println(ManejadorEvento.RESULTADO_OK);
			}

			else {
				out.println("ya existe ese nombre de usuario");
			}

		} catch (Exception e) {
			out.println(ManejadorEvento.RESULTADO_ERROR);
		}
	}

	public void conectarse() {
		try {

			String usuario = in.readLine();
			String password = in.readLine();

			boolean conectarse = usuarios.conectarse(usuario, password);

			if (conectarse) {
				Usuario usuarioConectado = usuarios.darUsuario(usuario);
				participantes.agregarNuevoParticipante(usuarioConectado);
			}

			else {
				out.println("Revisar el nombre de usuario o password");
			}

		} catch (Exception e) {
			out.println(ManejadorEvento.RESULTADO_ERROR);
		}
	}

	/**
	 * Se encarga de enviar la informacion del nuevo reto al cliente que se esta
	 * atendiendo
	 */
	public void darReto() {
		try {
			String codigos = in.readLine();
			Reto reto = retos.darRetoAleatorio(codigos);
			out.println(ManejadorEvento.RESULTADO_OK);
			out.println(reto.getCodigo());
			out.println(reto.getDescripcion());
			out.println(reto.getLatitud());
			out.println(reto.getLongitud());
			out.println(reto.getDelta());

		} catch (Exception e) {
			out.println(ManejadorEvento.RESULTADO_ERROR);
		}
	}

	/**
	 * se encarga de enviar la descripcion de una pista del reto solicitado por
	 * el cliente
	 */
	public void darPista() {
		try {
			String codigo = in.readLine();
			int consecutivo = Integer.parseInt(in.readLine());
			Reto reto = retos.darReto(codigo);
			String descrp = reto.darDescripcionPista(consecutivo);

			if (descrp.equals(""))
				out.println(descrp);
			else
				out.println("no hay mas retos para mostrar");

		} catch (Exception e) {
			out.println(ManejadorEvento.RESULTADO_ERROR);
		}
	}

}
