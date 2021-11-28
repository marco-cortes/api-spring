package fes.aragon.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fes.aragon.entity.personaje.Personaje;

@Repository
public interface PersonajeDao extends CrudRepository<Personaje, Integer>{
}
