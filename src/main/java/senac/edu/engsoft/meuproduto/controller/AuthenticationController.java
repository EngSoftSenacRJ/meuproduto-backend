package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.dto.JwtRequest;
import senac.edu.engsoft.meuproduto.model.dto.JwtResponse;
import senac.edu.engsoft.meuproduto.security.JwtTokenUtil;
import senac.edu.engsoft.meuproduto.security.UserDetailsCustom;
import senac.edu.engsoft.meuproduto.service.JwtUserDetailsServiceImpl;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Tag(name = "Autenticação de Usuário 'Administrador' e 'Funcionário'", description = "Autenticação de Usuário 'Administrador' e 'Funcionário' API")
public class AuthenticationController {
	

	private AuthenticationManager authenticationManager;
	private JwtTokenUtil jwtTokenUtil;
	private JwtUserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public AuthenticationController(AuthenticationManager authenticationManager,
									JwtTokenUtil jwtTokenUtil,
									JwtUserDetailsServiceImpl userDetailsService,
									BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@Operation(summary = "Autenticar usuário", description = "Autenticar usuário 'Administrador' ou 'Funcionário'")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetailsCustom userDetailsCustom = (UserDetailsCustom) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetailsCustom);

		return ResponseEntity.ok(new JwtResponse(token, userDetailsCustom.getUsername(), userDetailsCustom.getNome(), userDetailsCustom.getUsuarioType()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}