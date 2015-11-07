package logica;

import java.util.ArrayList;

public class Participantes {

	public ArrayList<Participante> participantes;
	
	public Participantes(){
		participantes = new ArrayList<Participante>();
	}
	
	public void agregarNuevoParticipante(Usuario usuario){
		
		Participante parti = new Participante(usuario);
		participantes.add(parti);
	}
	
	public void quitarParticipante(String usuario){
		boolean a = false;
		
		for(int i = 0; i< participantes.size() && !a; i++){
			if(participantes.get(i).darUsuario().equals(usuario)){
				participantes.remove(i);
				a = true;
			}
		}
	}
	
}
