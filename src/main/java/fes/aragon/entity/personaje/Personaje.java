package fes.aragon.entity.personaje;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fes.aragon.entity.pelicula.Pelicula;
import lombok.Data;

@Data
@Entity
@Table(name="personaje")
public class Personaje {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personaje")
	private int id;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="edad")
	private int edad;
	
	@Column(name="peso")
	private float peso;
	
	@Column(name="historia")
	private String historia;
	
	@JsonIgnoreProperties("personajes")
	@ManyToMany(mappedBy = "personajes", cascade = CascadeType.PERSIST)
	private List<Pelicula> peliculas;
	
	
	public void agregarPeliculas(Pelicula p) {
		if(peliculas == null) {
			peliculas = new ArrayList<>();
		}
		peliculas.add(p);
	}

	public Personaje() {}
	
	public Personaje(String imagen, String nombre, int edad, float peso, String historia) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
	}
	
}
