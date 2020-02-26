package senac.edu.engsoft.meuproduto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.EntityIdFoundForInsertOperationException;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.UsuarioType;
import senac.edu.engsoft.meuproduto.service.EmailService;
import senac.edu.engsoft.meuproduto.service.UsuarioService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin
public class RegisterController {

	private UsuarioService usuarioService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ServletContext context;
	private EmailService emailService;

	public RegisterController(UsuarioService usuarioService,
							  BCryptPasswordEncoder bCryptPasswordEncoder,
							  ServletContext context,
							  EmailService emailService) {
		this.usuarioService = usuarioService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.context = context;
		this.emailService = emailService;
	}

	@RequestMapping(value = "/register/confirmEmail", method = RequestMethod.GET)
//	ResponseEntity<?>
	public void confirmarEmail(HttpServletResponse response, @RequestParam Long id, @RequestParam String token) throws IOException {
		Optional<Usuario> usuario = usuarioService.getById(id);
		if(!usuario.isPresent() || token == null || !usuario.get().getTokenValidacaoEmail().equals(token)){
			//TODO: Implementar redirect para uma página de erro

			response.sendRedirect("http://www.google.com.br/search?q=falha");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else{
			Usuario usuarioEncontrado = usuario.get();
			usuarioEncontrado.setTokenValidacaoEmail(null);
			usuarioEncontrado.setEnabled(true);
			usuarioService.update(usuarioEncontrado);
			response.sendRedirect("http://www.google.com.br/search?q=sucesso");
//			return ResponseEntity.status(HttpStatus.OK).build();
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(HttpServletRequest request, @RequestBody @Valid Usuario usuario) {
		if(usuario.getId() != null){
			throw new EntityIdFoundForInsertOperationException(usuario.getId());
		}

		String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);

		String token = UUID.randomUUID().toString();
		usuario.setTokenValidacaoEmail(token);

		if(usuario.getUsuarioType().equals(UsuarioType.ADMINISTRADOR)){
			usuario = usuarioService.save(new UsuarioAdministrador(usuario));
		}else if (usuario.getUsuarioType().equals(UsuarioType.FUNCIONARIO)){
			usuario = usuarioService.save((UsuarioFuncionario) usuario);
		}

		try {
			String baseUrl = "http://"+request.getServerName()+":"+request.getLocalPort();
			emailService.sendEmailValidacaoNovoUsuario(usuario, baseUrl);
//			EmailUtil.sendEmailValidacaoNovoUsuario(usuario, context);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
