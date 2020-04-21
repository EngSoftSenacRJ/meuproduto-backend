package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.SearchRequestDTO;
import senac.edu.engsoft.meuproduto.model.resource.ProdutoSearchResponseResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.ProdutoSearchResponseResourceAssembler;
import senac.edu.engsoft.meuproduto.service.SearchService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/search"})
@Tag(name = "Search", description = "Search API")
public class SearchController {

	private SearchService searchService;
	private final ProdutoSearchResponseResourceAssembler produtoSearchResponseResourceAssembler;

	@Autowired
	public SearchController(SearchService searchService,
							ProdutoSearchResponseResourceAssembler produtoSearchResponseResourceAssembler) {
		this.searchService = searchService;
		this.produtoSearchResponseResourceAssembler = produtoSearchResponseResourceAssembler;
	}

	@GetMapping
	@Operation(summary = "Search products", description = "Search products")
	public ResponseEntity<CollectionModel<ProdutoSearchResponseResource>> search(@RequestBody @Valid SearchRequestDTO searchRequestDTO) {
		List<LojaProduto> lojaProdutos = (List<LojaProduto>) searchService.search(searchRequestDTO);
		return new ResponseEntity<>(produtoSearchResponseResourceAssembler.toCollectionModel(lojaProdutos), HttpStatus.OK);
	}

}
