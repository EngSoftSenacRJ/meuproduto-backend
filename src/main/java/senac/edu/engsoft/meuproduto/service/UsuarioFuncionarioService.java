package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;

import java.util.Optional;

public interface UsuarioFuncionarioService {

	Iterable<UsuarioFuncionario> getAll();
	Iterable<UsuarioFuncionario> getAllEnabled();
	Iterable<UsuarioFuncionario> getByUsernameAdministrador(String usernameAdministrador);
	Optional<UsuarioFuncionario> getById(Long id);
	Optional<UsuarioFuncionario> getByNome(String nome);
	UsuarioFuncionario save(UsuarioFuncionario usuarioFuncionario);
	UsuarioFuncionario update(Long id, UsuarioFuncionario usuarioFuncionario);
	void delete(Long id);
}
