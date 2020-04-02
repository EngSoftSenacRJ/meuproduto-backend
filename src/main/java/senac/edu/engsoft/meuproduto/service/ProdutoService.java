package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;

import java.util.Optional;

public interface ProdutoService {

	Iterable<Produto> search(String searchText);
	Optional<Produto> getById(Long id);
	Iterable<Produto> getAll();
	Produto save(ProdutoDTO produto);
	Produto update(Long id, Produto produto, Produto produtoAtual);
	void delete(Long id);
}
