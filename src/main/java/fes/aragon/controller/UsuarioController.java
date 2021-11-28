package fes.aragon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fes.aragon.entity.Usuario;
import fes.aragon.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
	@Autowired
	private UsuarioService us;
	
	@PostMapping("login")
	public void login(@RequestBody Usuario user, HttpServletResponse response) {
		response.setHeader("Authorization", us.login(user));
		
	}
	
	@PostMapping("register")
	public void register(@RequestBody Usuario user, HttpServletResponse response) throws IOException {
		response.setHeader("Authorization", us.register(user));
	}

	
}
