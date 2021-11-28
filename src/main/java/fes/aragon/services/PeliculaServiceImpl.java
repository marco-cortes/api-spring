package fes.aragon.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fes.aragon.dao.PeliculaDao;
import fes.aragon.dao.PersonajeDao;
import fes.aragon.entity.pelicula.GuardarPelicula;
import fes.aragon.entity.pelicula.ListaPeliculas;
import fes.aragon.entity.pelicula.Pelicula;

@Service
public class PeliculaServiceImpl implements PeliculaService{
	
	@Autowired
	private PeliculaDao pd;
	
	@Autowired
	private PersonajeDao perd;

	@Override
	public List<ListaPeliculas> getAll() {
		ArrayList<ListaPeliculas> aux = new ArrayList<>();
		
		for (Pelicula p : pd.findAll()) {
			aux.add(new ListaPeliculas(p.getImagen(), p.getTitulo(), p.getFecha()));
		}
		return aux;
	}

	@Override
	public void save(GuardarPelicula p) {
		Pelicula pelicula = new Pelicula(p.getImagen(), p.getTitulo(), p.getFecha(), p.getCalificacion(), p.getIdGenero());
		if(p.getId()>0) {
			pelicula.setId(p.getId());
		}
		if(p.getPersonajes() != null) {
			for(int i = 0; i < p.getPersonajes().size(); i++) {
				pelicula.agregarPersonajes(perd.findById(p.getPersonajes().get(i)).orElse(null));
			}
		} else {
			pelicula.setPersonajes(getById(p.getId()).getPersonajes());
		}
		pd.save(pelicula);
	}

	@Override
	public void delete(Pelicula p) {
		p.setPersonajes(new ArrayList<>());
		pd.save(p);
		pd.delete(p);
	}

	@Override
	public Pelicula getById(int id) {
		return pd.findById(id).orElse(null);
	}

	@Override
	public List<ListaPeliculas> getAllByName(String name) {
		ArrayList<ListaPeliculas> aux = new ArrayList<>();
		for (Pelicula p : pd.findAll()) {
			if(p.getTitulo().equals(name))
				aux.add(new ListaPeliculas(p.getImagen(), p.getTitulo(), p.getFecha()));
		}
		return aux;
	}

	@Override
	public List<ListaPeliculas> getAllByGenre(Integer genre) {
		ArrayList<ListaPeliculas> aux = new ArrayList<>();
		for (Pelicula p : pd.findAll()) {
			if(p.getIdGenero() == genre)
				aux.add(new ListaPeliculas(p.getImagen(), p.getTitulo(), p.getFecha()));
		}
		return aux;
	}

	@Override
	public List<ListaPeliculas> getAllByOrder(String order) {
		List<ListaPeliculas> aux = new ArrayList<>();
		for (Pelicula p : pd.findAll()) {
			aux.add(new ListaPeliculas(p.getImagen(), p.getTitulo(), p.getFecha()));
		}
		if(order.equals("ASC")) {
			Collections.sort(aux, new Comparator<ListaPeliculas>() {
				@Override
				public int compare(ListaPeliculas o1, ListaPeliculas o2) {
					return o1.getNombre().compareTo(o2.getNombre());
				}	
			});
		} else if(order.equals("DESC")) {
			Collections.sort(aux, new Comparator<ListaPeliculas>() {
				@Override
				public int compare(ListaPeliculas o1, ListaPeliculas o2) {
					return o1.getNombre().compareTo(o2.getNombre()) * -1;
				}	
			});
		}
		return aux;
	}
}
