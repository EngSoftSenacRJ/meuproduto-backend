package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;

import java.util.Optional;

public interface ProdutoService {

	Optional<Produto> getById(Long id);
	Iterable<Produto> getAll();
	Iterable<Produto> getByCategoriaIdAndMarcaId(Long categoriaId, Long marcaId);
	Produto save(ProdutoDTO produto);
	Produto update(Long id, Produto produto, Produto produtoAtual);
	void delete(Long id);
}
