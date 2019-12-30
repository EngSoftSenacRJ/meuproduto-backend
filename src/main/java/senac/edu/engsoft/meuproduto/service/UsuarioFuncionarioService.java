package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;

public interface UsuarioFuncionarioService {

	Iterable<UsuarioFuncionario> getAll();
	Optional<UsuarioFuncionario> getById(Long id);
	Optional<UsuarioFuncionario> getByNome(String nome);
	UsuarioFuncionario saveOrUpdate(UsuarioFuncionario usuarioFuncionario);
	void delete(Long id);
	void deleteAll();
}
