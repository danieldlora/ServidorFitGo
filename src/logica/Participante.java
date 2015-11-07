package logica;

public class Participante {

	
	private Usuario usuario;
	
	private long timeStart;
	
	public Participante(Usuario usuario){
		this.usuario = usuario;
		timeStart = System.currentTimeMillis();
	}
	
	public String darUsuario(){
		return usuario.getUsuario();
	}
	
	public long calcularTiempoConectado(){
		long b = System.currentTimeMillis();
		
		return b-timeStart;
	}
}
