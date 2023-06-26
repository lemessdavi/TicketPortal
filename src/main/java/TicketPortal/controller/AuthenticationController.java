package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import TicketPortal.dto.Login;
import TicketPortal.models.Usuario;
import TicketPortal.service.TokenService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		// criar token de autenticacao
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.username(), login.password());

		// autenticar usuario
		Authentication autenticacao = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		var usuario = (Usuario) autenticacao.getPrincipal();
		
		Gson g = new Gson();  
		 
		return g.toJson(tokenService.gerarToken(usuario));

	}
}
