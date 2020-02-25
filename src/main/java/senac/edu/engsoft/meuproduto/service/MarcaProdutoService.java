package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.MarcaProduto;

public interface MarcaProdutoService {

	Iterable<MarcaProduto> getAll();
	MarcaProduto save(MarcaProduto marcaProduto);
}
