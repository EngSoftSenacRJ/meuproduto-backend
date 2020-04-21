package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.advice.exception.ProdutoAlreadyExistException;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;
import senac.edu.engsoft.meuproduto.service.repository.ProdutoRepository;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;

	private CategoriaProdutoService categoriaProdutoService;
	private MarcaProdutoService marcaProdutoService;

	public ProdutoServiceImpl(ProdutoRepository produtoRepository,
							  CategoriaProdutoService categoriaProdutoService,
							  MarcaProdutoService marcaProdutoService) {
		super();
		this.produtoRepository = produtoRepository;
		this.categoriaProdutoService = categoriaProdutoService;
		this.marcaProdutoService = marcaProdutoService;
	}

	@Override
	public Produto save(ProdutoDTO _produtoDTO) {
		if(produtoRepository.getByNome(_produtoDTO.getNome()) != null){
			throw new ProdutoAlreadyExistException(_produtoDTO.getNome());
		}

		MarcaProduto _marcaProduto = marcaProdutoService.getById(_produtoDTO.getMarcaId()).orElseThrow(() -> new EntityModelNotFoundException(MarcaProduto.class, _produtoDTO.getMarcaId()));
		CategoriaProduto _categoriaProduto = categoriaProdutoService.getById(_produtoDTO.getCategoriaId()).orElseThrow(() -> new EntityModelNotFoundException(CategoriaProduto.class, _produtoDTO.getCategoriaId()));
		Produto _produto = new Produto();
		_produto.setMarca(_marcaProduto);
		_produto.setCategoria(_categoriaProduto);
		_produto.setMesesGarantia(_produtoDTO.getMesesGarantia());
		_produto.setDescricao(_produtoDTO.getDescricao());
		_produto.setNome(_produtoDTO.getNome());
		return produtoRepository.save(_produto);
	}

	@Override
	public Produto update(Long id, Produto produto, Produto produtoAtual) {
		produtoAtual.copyForNew(produto);
		return produtoRepository.save(produtoAtual); //update
	}

	@Override
	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Iterable<Produto> getAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Iterable<Produto> getByCategoriaIdAndMarcaId(Long categoriaId, Long marcaId) {
		return produtoRepository.getByCategoriaIdAndMarcaId(categoriaId, marcaId);
	}


}
