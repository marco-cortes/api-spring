package fes.aragon.services;

import java.util.List;

import fes.aragon.entity.personaje.ListaPersonajes;
import fes.aragon.entity.personaje.Personaje;

public interface PersonajeService {
	public List<ListaPersonajes> getAll();
	public List<ListaPersonajes> getAllByName(String name);
	public List<ListaPersonajes> getAllByAge(Integer age);
	public List<ListaPersonajes> getAllByMovie(Integer movie);
	public List<ListaPersonajes> getAllByWeight(Float weight);
	public void save(Personaje p);
	public void delete(Personaje p);
	public Personaje getById(int id);
}
