package senac.edu.engsoft.meuproduto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.UsuarioType;
import senac.edu.engsoft.meuproduto.service.UsuarioService;


@RestController
@CrossOrigin
public class RegisterController {

	private UsuarioService usuarioService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public RegisterController(UsuarioService usuarioService,
							  BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioService = usuarioService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody Usuario usuario) {
		String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		if(usuario.getUsuarioType().equals(UsuarioType.ADMINISTRADOR)){
			usuarioService.save(new UsuarioAdministrador(usuario));
		}else if (usuario.getUsuarioType().equals(UsuarioType.FUNCIONARIO)){
			usuarioService.save((UsuarioFuncionario) usuario);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}