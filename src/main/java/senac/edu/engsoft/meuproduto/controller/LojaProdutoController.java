package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;
import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.LojaProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.LojaProdutoService;
import senac.edu.engsoft.meuproduto.service.LojaService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping({"/lojasProdutos"})
@Tag(name = "Loja/Produto", description = "Loja/Produto API")
public class LojaProdutoController {

	private LojaService lojaService;
	private LojaProdutoService lojaProdutoService;
	private final LojaProdutoResourceAssembler lojaProdutoResourceAssembler;

	@Autowired
	public LojaProdutoController(LojaService lojaService,
								 LojaProdutoResourceAssembler lojaProdutoResourceAssembler,
								 LojaProdutoService lojaProdutoService) {
		this.lojaService = lojaService;
		this.lojaProdutoResourceAssembler = lojaProdutoResourceAssembler;
		this.lojaProdutoService = lojaProdutoService;
	}


	@GetMapping
	@Operation(summary = "Buscar Loja/Produto", description = "Buscar lista de Loja/Produto")
	public ResponseEntity<CollectionModel<LojaProdutoResource>> getAll() {
		return new ResponseEntity<>(lojaProdutoResourceAssembler.toCollectionModel(lojaService.getAll()), HttpStatus.OK);
	}

//	@ResponseStatus(value=HttpStatus.OK)
//	@GetMapping(value = "/{id}")
//	@Operation(summary = "Buscar Loja por 'id'", description = "Buscar Loja por 'id'")
//	public LojaResource getById(@PathVariable Long id) {
//		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		return lojaResourceAssembler.toModel(loja);
//	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@GetMapping(value="/loja")
//	@Operation(summary = "Buscar Loja por 'nome'", description = "Buscar Loja por 'nome'")
//	public LojaResource getByNome(@RequestParam(value="nome") String nome) {
//		Loja loja = lojaService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
//		return lojaResourceAssembler.toModel(loja);
//	}

	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar Loja/Produto", description = "Criar Loja/Produto")
	public ResponseEntity<Object> create(@RequestBody @Valid LojaProdutoDTO _lojaProdutoDTO) {
		LojaProduto lojaProduto = lojaProdutoService.save(_lojaProdutoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

//	@ResponseStatus(value=HttpStatus.OK)
//	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
//	@Operation(summary = "Atualizar Loja por 'id'", description = "Atualizar Loja por 'id'")
//	public LojaResource update(@RequestBody Loja _loja, @PathVariable Long id) {
//		Loja lojaAtual = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		Loja loja = lojaService.update(id, _loja, lojaAtual);
//		return lojaResourceAssembler.toModel(loja);
//	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@DeleteMapping(value = "/{id}")
//	@Operation(summary = "Remover Loja por 'id'", description = "Remover Loja por 'id'")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		lojaService.delete(loja.getId());
//		return ResponseEntity.noContent().build();
//	}
	
}
