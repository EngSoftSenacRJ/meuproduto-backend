package senac.edu.engsoft.meuproduto.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.service.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public JwtUserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.getByEmail(email);
		if (usuario.isPresent()) {
			if(usuario.isPresent() && usuario.get() instanceof UsuarioAdministrador)
				return (UsuarioAdministrador) usuario.get();
			if(usuario.isPresent() && usuario.get() instanceof UsuarioFuncionario)
				return (UsuarioFuncionario) usuario.get();
		}
		throw new UsernameNotFoundException("User not found with username: " + email);
	}
}
