package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Loja;

import java.util.Optional;

public interface LojaService {

	Iterable<Loja> getAll();
	Iterable<Loja> getLojasByUsernameAdministrador(String usernameAdministrador);
	Optional<Loja> getById(Long id);
	Optional<Loja> getByNome(String nome);
	Loja save(Loja loja);
	Loja update(Long id, Loja loja, Loja lojaAtual);
	void delete(Long id);
	void deleteAll();
}
