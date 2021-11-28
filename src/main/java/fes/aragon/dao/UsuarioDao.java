package fes.aragon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fes.aragon.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario,Integer>{
	Usuario findByUsername(String username);
}
