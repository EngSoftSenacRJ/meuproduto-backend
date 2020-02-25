package senac.edu.engsoft.meuproduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.resource.MarcaProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.MarcaProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.MarcaProdutoService;

@RestController
@RequestMapping({"/marcas"})
public class MarcaProdutoController {

	private MarcaProdutoService marcaProdutoService;
	private final MarcaProdutoResourceAssembler marcaProdutoResourceAssembler;

	@Autowired
	public MarcaProdutoController(MarcaProdutoService categoriaProdutoService,
								  MarcaProdutoResourceAssembler marcaProdutoResourceAssembler) {
		this.marcaProdutoService = categoriaProdutoService;
		this.marcaProdutoResourceAssembler = marcaProdutoResourceAssembler;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	public CollectionModel<MarcaProdutoResource> getAll() {;
		return marcaProdutoResourceAssembler.toCollectionModel(marcaProdutoService.getAll());
	}

	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody MarcaProduto _marcaProduto) {
		MarcaProduto marcaProduto = marcaProdutoService.save(_marcaProduto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
