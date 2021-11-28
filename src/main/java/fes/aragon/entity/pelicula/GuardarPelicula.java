package fes.aragon.entity.pelicula;

import java.util.ArrayList;

import lombok.Data;

@Data
public class GuardarPelicula {
	private int id;
	private String imagen;
	private String titulo;
	private String fecha;
	private int calificacion;
	private int idGenero;
	private ArrayList<Integer> personajes;
}
