package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;
import senac.edu.engsoft.meuproduto.service.repository.LojaProdutoRepository;

import java.util.Optional;

@Service
public class LojaProdutoServiceImpl implements LojaProdutoService {

	private final LojaProdutoRepository lojaProdutoRepository;
	private final LojaService lojaService;
	private final ProdutoService produtoService;

	public LojaProdutoServiceImpl(LojaProdutoRepository lojaProdutoRepository,
								  LojaService lojaService,
								  ProdutoService produtoService) {
		super();
		this.lojaProdutoRepository = lojaProdutoRepository;
		this.lojaService = lojaService;
		this.produtoService = produtoService;
	}

//	@Override
//	public Iterable<LojaProduto> getAll() {
//		return lojaProdutoRepository.findAll();
//	}

	@Override
	public LojaProduto save(LojaProdutoDTO lojaProdutoDTO) {
		Optional<Loja> loja = lojaService.getById(lojaProdutoDTO.getIdLoja());
		Optional<Produto> produto = produtoService.getById(lojaProdutoDTO.getIdProduto());
		if(!loja.isPresent()){
			throw new EntityModelNotFoundException(lojaProdutoDTO.getIdLoja());
		}
		if(!produto.isPresent()){
			throw new EntityModelNotFoundException(lojaProdutoDTO.getIdProduto());
		}
		return lojaProdutoRepository.save(new LojaProduto(loja.get(), produto.get(), lojaProdutoDTO.getPreco()));
	}

	//TODO:
//	@Override
//	public Loja update(LojaProdutoDTO lojaProdutoDTO) {
//
//		return lojaRepository.save(lojaAtual); //update
//	}

	@Override
	public void delete(Long id) {
		lojaProdutoRepository.deleteById(id);
	}
	
}
