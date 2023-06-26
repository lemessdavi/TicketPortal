package TicketPortal.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import TicketPortal.models.Usuario;


import com.auth0.jwt.algorithms.Algorithm;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	
	
	public String gerarToken(Usuario usuario) {
		return JWT.create()
				.withIssuer("Tarefas")
				.withSubject(usuario.getUsername())
				.withClaim("id", usuario.getIdUsuario())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(999)
						.toInstant(ZoneOffset.of("-03:00"))
				).sign(Algorithm.HMAC256("chave-secreta"));
	}

	public String getSubject(String token) {
		
		return JWT.require(Algorithm.HMAC256("chave-secreta"))
				.withIssuer("Tarefas")
				.build().verify(token).getSubject();
	}
}
