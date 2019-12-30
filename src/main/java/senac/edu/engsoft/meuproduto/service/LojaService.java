package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import senac.edu.engsoft.meuproduto.model.Loja;

public interface LojaService {

	Iterable<Loja> getAll();
	Optional<Loja> getById(Long id);
	Optional<Loja> getByNome(String nome);
	Loja saveOrUpdate(Loja loja);
	void delete(Long id);
	void deleteAll();
}
