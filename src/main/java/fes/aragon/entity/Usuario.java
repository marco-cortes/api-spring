package fes.aragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id;
	
	@Email
	@Column(name="email")
	private String username;
	
	@Column(name="pass")
	private String password;
	
	public Usuario() {}
	
	public Usuario(String email, String pass) {
		this.username=email;
		this.password=pass;
	}
}
