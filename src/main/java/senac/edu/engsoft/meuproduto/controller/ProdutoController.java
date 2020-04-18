package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;
import senac.edu.engsoft.meuproduto.model.resource.ProdutoResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.ProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.ProdutoService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping({"/produtos"})
@Tag(name = "Produto", description = "Produto API")
public class ProdutoController {

	private ProdutoService produtoService;
	private final ProdutoResourceAssembler produtoResourceAssembler;

	@Autowired
	public ProdutoController(ProdutoService produtoService,
							 ProdutoResourceAssembler produtoResourceAssembler) {
		this.produtoService = produtoService;
		this.produtoResourceAssembler = produtoResourceAssembler;
	}

	@GetMapping
	@Operation(summary = "Buscar Produtos", description = "Buscar lista de Produtos")
	public ResponseEntity<CollectionModel<ProdutoResource>> getAll() {
		return new ResponseEntity<>(produtoResourceAssembler.toCollectionModel(produtoService.getAll()), HttpStatus.OK);
	}

	@GetMapping
	@Operation(summary = "Buscar Produtos por 'categoria' e 'marca'", description = "Buscar lista de Produtos por ID da Categoria e ID da Marca")
	public ResponseEntity<CollectionModel<ProdutoResource>> getByCategoriaIdAndMarcaId(@RequestParam(value="categoriaId") Long categoriaId, @RequestParam(value="marcaId") Long marcaId) {
		return new ResponseEntity<>(produtoResourceAssembler.toCollectionModel(produtoService.getByCategoriaIdAndMarcaId(categoriaId, marcaId)), HttpStatus.OK);
	}

//	@ResponseStatus(value=HttpStatus.OK)
//	@GetMapping(value = "/{id}")
//	public LojaResource getById(@PathVariable Long id) {
//		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		return lojaResourceAssembler.toModel(loja);
//	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@GetMapping(value="/loja")
//	public LojaResource getByNome(@RequestParam(value="nome") String nome) {
//		Loja loja = lojaService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
//		return lojaResourceAssembler.toModel(loja);
//	}
//
	@ResponseStatus(value= HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid ProdutoDTO _produtoDTO) {
		produtoService.save(_produtoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
//	public LojaResource update(@RequestBody Loja _loja, @PathVariable Long id) {
//		Loja lojaAtual = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		Loja loja = lojaService.update(id, _loja, lojaAtual);
//		return lojaResourceAssembler.toModel(loja);
//	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
//		lojaService.delete(loja.getId());
//		return ResponseEntity.noContent().build();
//	}

}
