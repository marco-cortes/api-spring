package fes.aragon.entity.pelicula;

import lombok.Data;

@Data
public class ListaPeliculas {
	private String imagen;
	private String nombre;
	private String fecha;
	
	public ListaPeliculas(String imagen, String nombre, String fecha) {
		this.imagen=imagen;
		this.nombre=nombre;
		this.fecha=fecha;
	}
}
