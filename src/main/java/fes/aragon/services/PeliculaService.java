package fes.aragon.services;

import java.util.List;

import fes.aragon.entity.pelicula.GuardarPelicula;
import fes.aragon.entity.pelicula.ListaPeliculas;
import fes.aragon.entity.pelicula.Pelicula;

public interface PeliculaService {
	public List<ListaPeliculas> getAll();
	public List<ListaPeliculas> getAllByName(String name);
	public List<ListaPeliculas> getAllByGenre(Integer genre);
	public List<ListaPeliculas> getAllByOrder(String order);
	public void save(GuardarPelicula p);
	public void delete(Pelicula p);
	public Pelicula getById(int id);
}
