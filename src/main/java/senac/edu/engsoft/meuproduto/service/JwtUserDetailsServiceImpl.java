package senac.edu.engsoft.meuproduto.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.service.repository.UsuarioAdministradorRepository;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioAdministradorRepository usuarioAdministradorRepository;

	public JwtUserDetailsServiceImpl(UsuarioAdministradorRepository usuarioAdministradorRepository) {
		this.usuarioAdministradorRepository = usuarioAdministradorRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UsuarioAdministrador> usuarioAdministrador = usuarioAdministradorRepository.getByEmail(email);
		if (usuarioAdministrador.isPresent()) {
			return usuarioAdministrador.get();
		} else {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}
}
