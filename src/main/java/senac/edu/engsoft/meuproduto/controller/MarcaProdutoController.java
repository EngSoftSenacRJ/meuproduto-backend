package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Marca do Produto", description = "Marca do Produto API")
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
	@GetMapping(value = "/")
	@Operation(summary = "Buscar Marcas", description = "Buscar lista de Marcas dos produtos")
	public CollectionModel<MarcaProdutoResource> getAll() {;
		return marcaProdutoResourceAssembler.toCollectionModel(marcaProdutoService.getAll());
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/enabled")
	@Operation(summary = "Buscar Marcas habilitadas", description = "Buscar lista de Marcas dos produtos que estão habilitadas")
	public CollectionModel<MarcaProdutoResource> getAllEnabled() {;
		return marcaProdutoResourceAssembler.toCollectionModel(marcaProdutoService.getAllEnabled());
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar Marca por 'id'", description = "Buscar Marca do produto por 'id'")
	public MarcaProdutoResource getById(@PathVariable Long id) {
		MarcaProduto marcaProduto = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return marcaProdutoResourceAssembler.toModel(marcaProduto);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Desabilitar Marca por 'id'", description = "Desabilitar Marca do produto por 'id'")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		MarcaProduto marcaProduto = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		marcaProdutoService.desabilitar(marcaProduto);
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	@Operation(summary = "Atualizar Marca por 'id'", description = "Atualizar Marca do produto por 'id'")
	public MarcaProdutoResource update(@RequestBody MarcaProduto _marcaProduto, @PathVariable Long id) {
		MarcaProduto marcaProdutoParaAtualizar = marcaProdutoService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		marcaProdutoParaAtualizar.copyForNew(_marcaProduto);
		MarcaProduto marcaProdutoAtualizado = marcaProdutoService.update(marcaProdutoParaAtualizar);
		return marcaProdutoResourceAssembler.toModel(marcaProdutoAtualizado);
	}

	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar Marca", description = "Criar Marca do produto")
	public MarcaProdutoResource create(@RequestBody MarcaProduto _marcaProduto) {
		MarcaProduto marcaProduto = marcaProdutoService.save(_marcaProduto);
		return marcaProdutoResourceAssembler.toModel(marcaProduto);
	}
	
}
