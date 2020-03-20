package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.MarcaProduto;

import java.util.Optional;

public interface MarcaProdutoService {

	Iterable<MarcaProduto> getAll();
	Optional<MarcaProduto> getById(Long id);
	MarcaProduto update(MarcaProduto marcaProduto);
	MarcaProduto save(MarcaProduto marcaProduto);
	void desabilitar(MarcaProduto marcaProduto);
	Iterable<MarcaProduto> getAllEnabled();
}
