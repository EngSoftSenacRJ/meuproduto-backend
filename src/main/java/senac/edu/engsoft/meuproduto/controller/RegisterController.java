package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cadastro Usuário Administrador", description = "Cadastro Usuário Administrador API")
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
	@Operation(summary = "Confirmar Email cadastrado", description = "Confirmar Email cadastrado")
	public void confirmarEmail(HttpServletResponse response, @RequestParam Long id, @RequestParam String token) throws IOException {
		Optional<Usuario> usuario = usuarioService.getById(id);
		if(!usuario.isPresent() || token == null ||
				(!usuario.get().isEnabled() && !usuario.get().getTokenValidacaoEmail().equals(token))
		){
			response.sendRedirect("http://localhost:4200/login?status=erro");
		}else{
			Usuario usuarioEncontrado = usuario.get();
			if(!usuarioEncontrado.isEnabled()) {
				usuarioEncontrado.setEnabled(true);
				usuarioEncontrado.setTokenValidacaoEmail(null);
				usuarioService.update(usuarioEncontrado);
			}
			response.sendRedirect("http://localhost:4200/login?status=sucesso");
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@Operation(summary = "Cadastrado usuário", description = "Cadastrar usuário 'Administrador' ou 'Funcionário'")
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
