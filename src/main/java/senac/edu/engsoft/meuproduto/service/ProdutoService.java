package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Produto;

public interface ProdutoService {

	Iterable<Produto> search(String searchText);
	Iterable<Produto> getAll();
	Produto save(Produto produto);
	Produto update(Long id, Produto produto, Produto produtoAtual);
	void delete(Long id);
}
