package senac.edu.engsoft.meuproduto.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.security.UserDetailsCustom;
import senac.edu.engsoft.meuproduto.service.repository.UsuarioRepository;

import java.util.ArrayList;
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

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		if (usuario.isPresent()) {
			Usuario usuarioFound = usuario.get();
			UserDetailsCustom userDetailsCustom =  new UserDetailsCustom(usuarioFound.getUsername(),
					usuarioFound.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
					accountNonLocked, new ArrayList<GrantedAuthority>());
			userDetailsCustom.setNome(usuarioFound.getNome());
			userDetailsCustom.setUsuarioType(usuarioFound.getUsuarioType());

			return userDetailsCustom;
//			if(usuario.isPresent() && usuario.get() instanceof UsuarioAdministrador)
//				return (UsuarioAdministrador) usuario.get();
//			if(usuario.isPresent() && usuario.get() instanceof UsuarioFuncionario)
//				return (UsuarioFuncionario) usuario.get();
		}
		throw new UsernameNotFoundException("User not found with username: " + email);
	}
}
