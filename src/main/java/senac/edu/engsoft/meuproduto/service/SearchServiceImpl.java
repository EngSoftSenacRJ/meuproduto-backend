package senac.edu.engsoft.meuproduto.service;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senac.edu.engsoft.meuproduto.advice.exception.LatitudeOrLongitudeNotInformedException;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.SearchRequestDTO;
import senac.edu.engsoft.meuproduto.service.repository.LojaProdutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

	@PersistenceContext
	private EntityManager entityManager;

	private final LojaProdutoRepository lojaProdutoRepository;

	public SearchServiceImpl(EntityManager entityManager,
							 LojaProdutoRepository lojaProdutoRepository) {
		super();
		this.entityManager = entityManager;
		this.lojaProdutoRepository = lojaProdutoRepository;
	}

	@Override
	public void indexAll() throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();
	}

	@Override
	public Iterable<LojaProduto> search(SearchRequestDTO searchRequestDTO) {
		if((searchRequestDTO.getLatitude() == null &&
				searchRequestDTO.getLongitude() != null) ||
				(searchRequestDTO.getLatitude() != null &&
						searchRequestDTO.getLongitude() == null)) {
			throw new LatitudeOrLongitudeNotInformedException();
		}

		List<LojaProduto> lojaProdutos = new ArrayList<>();
		if(searchRequestDTO.getIdMarca() != null ||
				searchRequestDTO.getIdCategoria() != null ||
				searchRequestDTO.getIdLoja() != null){
			lojaProdutos = searchByMarcaCategoriaAndLoja(searchRequestDTO.getIdMarca(),
					searchRequestDTO.getIdCategoria(),
					searchRequestDTO.getIdLoja());


			if (lojaProdutos != null && searchRequestDTO.getNomeProduto() != null) {
				lojaProdutos.stream().filter(
						lojaProduto -> lojaProduto.getProduto().getNome().concat(lojaProduto.getProduto().getDescricao()).toLowerCase()
								.contains(searchRequestDTO.getNomeProduto())
				).collect(Collectors.toList());
			}

		} else {
			//TODO retornar lojaProduto de "produtos"
			return searchByNomeProduto(searchRequestDTO.getNomeProduto());
		}

		/*
		Filtro de aproximação
		 */
		if(lojaProdutos != null && searchRequestDTO.getLatitude() != null && searchRequestDTO.getLongitude() != null) {
			lojaProdutos.stream().filter(lojaProduto ->
					lojaProduto != null && distance(lojaProduto.getLoja().getLatitude(), lojaProduto.getLoja().getLongitude(),
							searchRequestDTO.getLatitude(), searchRequestDTO.getLongitude()) <= searchRequestDTO.getDistanceKM())
					.collect(Collectors.toList());
		}
		return lojaProdutos;
	}

	private List<LojaProduto> searchByMarcaCategoriaAndLoja(Long idMarca, Long idCategoria, Long idLoja) {
		return lojaProdutoRepository.searchByMarcaCategoriaAndLoja(idMarca, idCategoria, idLoja);
	}

	private List<LojaProduto> searchByNomeProduto(String nomeProduto) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(LojaProduto.class)
				.get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder
				.keyword()
				.onField("produto.nome")
				.boostedTo(3)
				.matching(nomeProduto)
				.createQuery();

		Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, LojaProduto.class);
		List<LojaProduto> lojaProdutos = fullTextQuery.getResultList();
		return lojaProdutos;
	}

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		if(lat1 == 0 || lon1 == 0){
			return 0;
		}
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344; //KM
			return (dist);
		}
	}

}
