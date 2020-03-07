package senac.edu.engsoft.meuproduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.resource.MarcaProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.MarcaProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.MarcaProdutoService;

@CrossOrigin
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

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public MarcaProdutoResource getById(@PathVariable Long id) {
		MarcaProduto marcaProduto = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return marcaProdutoResourceAssembler.toModel(marcaProduto);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		MarcaProduto marcaProduto = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		marcaProdutoService.delete(marcaProduto.getId());
		return ResponseEntity.noContent().build();
	}

//	@ResponseStatus(value=HttpStatus.OK)
//	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
//	public MarcaProdutoResource update(@RequestBody MarcaProduto _marcaProduto, @PathVariable Long id) {
//		MarcaProduto marcaProdutoAtual = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		MarcaProduto marcaProduto = marcaProdutoService.update(id, _marcaProduto, marcaProdutoAtual);
//		return marcaProdutoResourceAssembler.toModel(marcaProduto);
//	}

	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody MarcaProduto _marcaProduto) {
		MarcaProduto marcaProduto = marcaProdutoService.save(_marcaProduto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
