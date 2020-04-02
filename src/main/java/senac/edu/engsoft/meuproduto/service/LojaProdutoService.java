package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;

public interface LojaProdutoService {

//	Iterable<LojaProduto> getAll();
	LojaProduto save(LojaProdutoDTO lojaProdutoDTO);
//	Loja update(Long id, Loja loja, Loja lojaAtual);
	void delete(Long id);
}
