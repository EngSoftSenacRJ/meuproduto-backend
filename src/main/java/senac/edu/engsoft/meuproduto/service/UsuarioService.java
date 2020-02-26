package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

	Usuario save(Usuario usuario);
	Optional<Usuario> getById(Long id);
	Usuario update(Usuario usuario);
	
}
