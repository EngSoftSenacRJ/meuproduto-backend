package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;

public interface LojaProdutoService {

	Iterable<LojaProduto> getAll();
	LojaProduto save(LojaProdutoDTO lojaProdutoDTO);
	void delete(Long id);
    LojaProduto update(LojaProdutoDTO lojaProdutoDTO);
	LojaProduto getByLojaIdAndProdutoId(Long lojaId, Long produtoId);
}
