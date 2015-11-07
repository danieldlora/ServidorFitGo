package logica;

import java.util.HashMap;

public class Usuarios {

	HashMap<String, Usuario> usuarios;
	
	public Usuarios(){
		usuarios = new HashMap<String, Usuario>();
	}
	
	
	public boolean registrarUsuario(String usuario, String password){
		if(!verificarUsuario(usuario)){
			Usuario newUser = new Usuario(usuario, password);
			usuarios.put(usuario, newUser);
			
			return true;
		}
			
		else return false;
	}
	
	private boolean verificarUsuario(String usuario){
		return usuarios.containsKey(usuario);
	}
	
	public Usuario darUsuario(String usuario){
		return usuarios.get(usuario);
	}
	
	 public boolean conectarse(String usuario, String password){
		if(verificarUsuario(usuario)){
			Usuario x = usuarios.get(usuario);
			
			if(x.getPassword().equals(password)){
				return true;
			}
			
			else return false;
		}
		
		else return false;
	}
}
