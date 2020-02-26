package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

import java.util.Optional;

public interface UsuarioAdministradorService {

	Iterable<UsuarioAdministrador> getAll();
	Optional<UsuarioAdministrador> getById(Long id);
	Optional<UsuarioAdministrador> getByUsername(String username);
	Optional<UsuarioAdministrador> getByNome(String nome);
	Optional<UsuarioAdministrador> getByCpf(Long cpf);
	Optional<UsuarioAdministrador> getByEmail(String email);
//	UsuarioAdministrador save(UsuarioAdministrador usuarioAdministrador);
	UsuarioAdministrador update(UsuarioAdministrador usuarioAdministrador);
	void delete(Long id);
	void deleteAll();
}
