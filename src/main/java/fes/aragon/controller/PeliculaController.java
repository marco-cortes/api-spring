package fes.aragon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fes.aragon.entity.pelicula.GuardarPelicula;
import fes.aragon.entity.pelicula.ListaPeliculas;
import fes.aragon.entity.pelicula.Pelicula;
import fes.aragon.services.PeliculaService;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
	
	@Autowired
	private PeliculaService ps;
	
	@GetMapping("")
	public List<ListaPeliculas> all(@RequestParam(required = false) String name, @RequestParam(required = false) Integer genre, 
																 @RequestParam(required = false) String order){
		if(name == null && genre == null && order == null) {
			return ps.getAll();
		} else if(name!=null) {
			return ps.getAllByName(name);
		} else if(genre!=null) {
			return ps.getAllByGenre(genre);
		} else if (order!=null) {
			if(order.equals("ASC") || order.equals("DESC"))
				return ps.getAllByOrder(order);
			else return null;
		}
		return null;
	}
	
	@PostMapping("/save")
	public void guardar(@RequestBody GuardarPelicula p) {
		ps.save(p);
	}
	
	@GetMapping("/{id}")
	public Pelicula getPersonaje(@PathVariable int id) {
		return ps.getById(id);
	}
	
	@PutMapping("/update/{id}")
	public void actualizar(@RequestBody GuardarPelicula p, @PathVariable int id) {
		p.setId(id);
		ps.save(p);
	}
	
	@DeleteMapping("/delete/{id}")
	public void eliminar(@PathVariable int id) {
		ps.delete(ps.getById(id));
	}
	
}
