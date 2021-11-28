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

import fes.aragon.entity.personaje.ListaPersonajes;
import fes.aragon.entity.personaje.Personaje;
import fes.aragon.services.PersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
	
	@Autowired
	private PersonajeService ps;
	
	@GetMapping("")
	public List<ListaPersonajes> all(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age, 
																	 @RequestParam(required = false) Integer movies, @RequestParam(required = false) Float weight){
		if(name == null && age == null && movies == null && weight==null) {
			return ps.getAll();
		} else if(name!=null) {
			return ps.getAllByName(name);
		} else if(age!=null) {
			return ps.getAllByAge(age);
		} else if (movies!=null) {
			return ps.getAllByMovie(movies);
		} else if(weight!=null) {
			return ps.getAllByWeight(weight);
		}
		return null;
	}
	
	@PostMapping("/save")
	public void guardar(@RequestBody Personaje p) {
		ps.save(p);
	}
	
	@GetMapping("/{id}")
	public Personaje getPersonaje(@PathVariable int id) {
		return ps.getById(id);
	}
	
	@PutMapping("/update/{id}")
	public void actualizar(@RequestBody Personaje p, @PathVariable int id) {
		if(ps.getById(id) != null) {
			p.setId(id);
			ps.save(p);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void eliminar(@PathVariable int id) {
		ps.delete(ps.getById(id));
	}
	
}
