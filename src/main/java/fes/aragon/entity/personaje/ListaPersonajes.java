package fes.aragon.entity.personaje;

import lombok.Data;

@Data
public class ListaPersonajes {
	private String imagen;
	private String nombre;
	
	public ListaPersonajes(String imagen, String nombre) {
		this.imagen=imagen;
		this.nombre=nombre;
	}
}
