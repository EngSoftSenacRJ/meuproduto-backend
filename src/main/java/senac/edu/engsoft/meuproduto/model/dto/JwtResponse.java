package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import senac.edu.engsoft.meuproduto.model.UsuarioType;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	private final String nome;
	private final UsuarioType usuarioType;

	public JwtResponse(String jwttoken, String username, String nome, UsuarioType usuarioType) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.nome = nome;
		this.usuarioType = usuarioType;
	}

}