package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;
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
//	private final LojaProdutoResourceAssembler lojaProdutoResourceAssembler;

	@Autowired
	public LojaProdutoController(LojaService lojaService,
								 LojaProdutoService lojaProdutoService) {
		this.lojaService = lojaService;
		this.lojaProdutoService = lojaProdutoService;
	}

//	@GetMapping
//	@Operation(summary = "Buscar Loja/Produto", description = "Buscar lista de Loja/Produto")
//	public ResponseEntity<CollectionModel<LojaProdutoResource>> getAll() {
//		return new ResponseEntity<>(lojaProdutoResourceAssembler.toCollectionModel(lojaProdutoService.getAll()), HttpStatus.OK);
//	}

	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar Loja/Produto", description = "Criar Loja/Produto")
	public ResponseEntity<Object> create(@RequestBody @Valid LojaProdutoDTO _lojaProdutoDTO) {
		LojaProduto lojaProduto = lojaProdutoService.save(_lojaProdutoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping(produces="application/json", consumes="application/json")
	@Operation(summary = "Atualizar preco LojaProduto", description = "Atualizar preco da associação LojaProduto")
	public ResponseEntity<Object> update(@RequestBody LojaProdutoDTO _lojaProdutoDTO) {
		LojaProduto lojaProduto = lojaProdutoService.update(_lojaProdutoDTO);
//		return lojaProdutoResourceAssembler.toModel(lojaProduto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remover LojaProduto por 'id'", description = "Remover LojaProduto por 'id'")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		lojaProdutoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
