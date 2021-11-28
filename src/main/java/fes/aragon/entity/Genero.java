package fes.aragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_genero")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="imagen")
	private String imagen;
	
	public Genero(String nombre, String imagen) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
	}
	
}
