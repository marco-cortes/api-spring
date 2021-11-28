package fes.aragon.entity.pelicula;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fes.aragon.entity.personaje.Personaje;
import lombok.Data;

@Data
@Entity
@Table(name="pelicula")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pelicula")
	private int id;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="calificacion")
	private int calificacion;
	
	@Column(name="id_genero")
	private int idGenero;
	
	@JsonIgnoreProperties("peliculas")
	@JoinTable(
			name="personaje_peliculas",
			joinColumns = @JoinColumn(name="id_pelicula"),
			inverseJoinColumns = @JoinColumn(name="id_personaje")
	)
	@ManyToMany	(cascade = CascadeType.PERSIST)
	private List<Personaje> personajes;
	
	public void agregarPersonajes(Personaje p) {
		if(personajes == null) {
			personajes = new ArrayList<>();
		}
		personajes.add(p);
	}
	
	public Pelicula() {}
	
	public Pelicula(String imagen, String titulo, String fecha, int calificacion, int idGenero) {
		super();
		this.imagen = imagen;
		this.titulo = titulo;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.idGenero=idGenero;
	}
	
}
