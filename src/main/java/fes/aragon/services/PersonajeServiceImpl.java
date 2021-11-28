package fes.aragon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fes.aragon.dao.PersonajeDao;
import fes.aragon.entity.personaje.ListaPersonajes;
import fes.aragon.entity.personaje.Personaje;

@Service
public class PersonajeServiceImpl implements PersonajeService{
	
	@Autowired
	private PersonajeDao pd;
	
	@Autowired
	private PeliculaService ps;
	
	@Override
	public List<ListaPersonajes> getAll() {
		ArrayList<ListaPersonajes> aux = new ArrayList<>();
		for (Personaje p : pd.findAll()) {
			aux.add(new ListaPersonajes(p.getImagen(), p.getNombre()));
		}
		return aux;
	}

	@Override
	public void save(Personaje p) {
		if(p != null) {
			pd.save(p);
		}
	}

	@Override
	public void delete(Personaje p) {
		if(p != null ) {
			for(int i = 0; i < p.getPeliculas().size(); i++) {
				for(int j = 0; j < p.getPeliculas().get(i).getPersonajes().size(); j++) {
					if(p.getId() == p.getPeliculas().get(i).getPersonajes().get(j).getId()) {
						p.getPeliculas().get(i).getPersonajes().remove(j);
					}	
				}
			}
			p.setPeliculas(new ArrayList<>());
			pd.save(p);
			pd.delete(p);
		}
	}

	@Override
	public Personaje getById(int id) {
		return pd.findById(id).orElse(null);
	}

	@Override
	public List<ListaPersonajes> getAllByName(String name) {
		List<ListaPersonajes> aux = new ArrayList<>();
		for (Personaje p : pd.findAll()) {
			if(p.getNombre().equals(name))
				aux.add(new ListaPersonajes(p.getImagen(), p.getNombre()));
		}
		return aux;
	}

	@Override
	public List<ListaPersonajes> getAllByAge(Integer age) {
		List<ListaPersonajes> aux = new ArrayList<>();
		for (Personaje p : pd.findAll()) {
			if(p.getEdad()==age)
				aux.add(new ListaPersonajes(p.getImagen(), p.getNombre()));
		}
		return aux;
	}

	@Override
	public List<ListaPersonajes> getAllByMovie(Integer movie) {
		List<ListaPersonajes> aux = new ArrayList<>();
		if(ps.getById(movie) == null)	
			return aux;
		for(Personaje p : ps.getById(movie).getPersonajes()) {
			aux.add(new ListaPersonajes(p.getImagen(), p.getNombre()));
		}
		return aux;
	}

	@Override
	public List<ListaPersonajes> getAllByWeight(Float weight) {
		List<ListaPersonajes> aux = new ArrayList<>();
		for (Personaje p : pd.findAll()) {
			if(p.getPeso()==weight)
				aux.add(new ListaPersonajes(p.getImagen(), p.getNombre()));
		}
		return aux;
	}
}
