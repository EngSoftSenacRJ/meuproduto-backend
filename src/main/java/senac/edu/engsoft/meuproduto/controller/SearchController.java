package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.resource.ProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.ProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.ProdutoService;

@CrossOrigin
@RestController
@RequestMapping({"/search"})
@Tag(name = "Search", description = "Search API")
public class SearchController {

	private ProdutoService produtoService;
	private final ProdutoResourceAssembler produtoResourceAssembler;

	@Autowired
	public SearchController(ProdutoService produtoService, ProdutoResourceAssembler produtoResourceAssembler) {
		this.produtoService = produtoService;
		this.produtoResourceAssembler = produtoResourceAssembler;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	@Operation(summary = "", description = "")
	public ResponseEntity<CollectionModel<ProdutoResource>> search(@RequestParam(value="search", required=true) String searchText) {
		return new ResponseEntity<>(produtoResourceAssembler.toCollectionModel(produtoService.search(searchText)), HttpStatus.OK);
	}

}
