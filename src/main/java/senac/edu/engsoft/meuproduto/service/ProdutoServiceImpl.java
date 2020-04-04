package senac.edu.engsoft.meuproduto.service;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.advice.exception.ProdutoAlreadyExistException;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;
import senac.edu.engsoft.meuproduto.service.repository.ProdutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@PersistenceContext
	private EntityManager entityManager;

	private final ProdutoRepository produtoRepository;

	private CategoriaProdutoService categoriaProdutoService;
	private MarcaProdutoService marcaProdutoService;

	public ProdutoServiceImpl(ProdutoRepository produtoRepository,
							  EntityManager entityManager,
							  CategoriaProdutoService categoriaProdutoService,
							  MarcaProdutoService marcaProdutoService) {
		super();
		this.produtoRepository = produtoRepository;
		this.entityManager = entityManager;
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
	public void indexAll() throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();
	}

	@Override
	public Iterable<Produto> search(String searchText) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Produto.class)
				.get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder
				.keyword()
				.wildcard()
				.onFields("nome", "descricao")
				.matching(searchText + "*")
				.createQuery();

//		org.apache.lucene.search.Query luceneQuery = queryBuilder
//				.all()
//				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Produto.class);
		Iterable<Produto> produtos = jpaQuery.getResultList();
		return produtos;
	}

	@Override
	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Iterable<Produto> getAll() {
		return produtoRepository.findAll();
	}
	
}
