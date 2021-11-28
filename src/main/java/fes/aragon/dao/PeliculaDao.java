package fes.aragon.dao;

import org.springframework.data.repository.CrudRepository;

import fes.aragon.entity.pelicula.Pelicula;

public interface PeliculaDao  extends CrudRepository<Pelicula, Integer>{

}
