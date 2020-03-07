package senac.edu.engsoft.meuproduto.controller;

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
	public CollectionModel<CategoriaProdutoResource> getAll() {;
		return categoriaProdutoResourceAssembler.toCollectionModel(categoriaProdutoService.getAll());
	}
	
}
