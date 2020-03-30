package senac.edu.engsoft.meuproduto.service;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.service.repository.ProdutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@PersistenceContext
	private EntityManager entityManager;

	private final ProdutoRepository produtoRepository;

	public ProdutoServiceImpl(ProdutoRepository produtoRepository, EntityManager entityManager) {
		super();
		this.produtoRepository = produtoRepository;
		this.entityManager = entityManager;
	}

	@Override
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
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

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Produto.class);
		return jpaQuery.getResultList();
	}

	@Override
	public Iterable<Produto> getAll() {
		return produtoRepository.findAll();
	}
	
}
