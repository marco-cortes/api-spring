package fes.aragon.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fes.aragon.dao.UsuarioDao;
import fes.aragon.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private MailService ms;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		var roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}
	
	public String register(Usuario user) throws IOException {
		if(user.getPassword().isEmpty() && user.getUsername().isEmpty()) {
			return null;
		}
		usuarioDao.save(user);
		ms.sendTextEmail(user.getUsername());
		return getToken(user.getUsername());
	}
	
	public String login(Usuario user) {
		Usuario usuario = usuarioDao.findByUsername(user.getUsername());
		if(usuario == null) {
			return null;
		} else {
			if(user.getPassword().isEmpty() && user.getUsername().isEmpty()) {
				return null;
			}
			if(usuario.getPassword().equals(user.getPassword())) {
				return getToken(user.getUsername());
			}
		}
		return null;
	}
	
	private String getToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts
				.builder()
				.setId(username)
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer  " + token;
	}
	
}
