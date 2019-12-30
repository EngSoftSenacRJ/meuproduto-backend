package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

public interface UsuarioAdministradorService {

	Iterable<UsuarioAdministrador> getAll();
	Optional<UsuarioAdministrador> getById(Long id);
	Optional<UsuarioAdministrador> getByNome(String nome);
	UsuarioAdministrador saveOrUpdate(UsuarioAdministrador usuarioAdministrador);
	void delete(Long id);
	void deleteAll();
}
