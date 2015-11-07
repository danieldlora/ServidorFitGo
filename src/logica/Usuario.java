package logica;

public class Usuario {

	/**
	 * nombre de usuario
	 */
	private String usuario;
	
	/**
	 * password del usuario
	 */
	private String password;
	
	/**
	 * constructor de la clase
	 * @param usuario
	 * @param password
	 */
	public Usuario(String usuario, String password){
		this.usuario = usuario;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}
	
	
}
