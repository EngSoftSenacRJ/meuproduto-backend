package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.resource.CategoriaProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.CategoriaProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.CategoriaProdutoService;

@CrossOrigin
@RestController
@RequestMapping({"/categorias"})
@Tag(name = "Categoria do Produto", description = "Categoria do Produto API")
public class CategoriaProdutoController {

	private CategoriaProdutoService categoriaProdutoService;
	private final CategoriaProdutoResourceAssembler categoriaProdutoResourceAssembler;

	@Autowired
	public CategoriaProdutoController(CategoriaProdutoService categoriaProdutoService,
			CategoriaProdutoResourceAssembler categoriaProdutoResourceAssembler) {
		this.categoriaProdutoService = categoriaProdutoService;
		this.categoriaProdutoResourceAssembler = categoriaProdutoResourceAssembler;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	@Operation(summary = "Buscar Categorias", description = "Buscar lista de Categorias de produtos")
	public CollectionModel<CategoriaProdutoResource> getAll() {;
		return categoriaProdutoResourceAssembler.toCollectionModel(categoriaProdutoService.getAll());
	}
	
}
