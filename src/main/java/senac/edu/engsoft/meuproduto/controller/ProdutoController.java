package senac.edu.engsoft.meuproduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.ProdutoDTO;
import senac.edu.engsoft.meuproduto.model.resource.assembler.ProdutoResourceAssembler;
import senac.edu.engsoft.meuproduto.service.CategoriaProdutoService;
import senac.edu.engsoft.meuproduto.service.MarcaProdutoService;
import senac.edu.engsoft.meuproduto.service.ProdutoService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {

	private ProdutoService produtoService;
	private CategoriaProdutoService categoriaProdutoService;
	private MarcaProdutoService marcaProdutoService;
	private final ProdutoResourceAssembler produtoResourceAssembler;

	@Autowired
	public ProdutoController(ProdutoService produtoService,
							 ProdutoResourceAssembler produtoResourceAssembler,
							 CategoriaProdutoService categoriaProdutoService,
							 MarcaProdutoService marcaProdutoService) {
		this.produtoService = produtoService;
		this.produtoResourceAssembler = produtoResourceAssembler;
		this.categoriaProdutoService = categoriaProdutoService;
		this.marcaProdutoService = marcaProdutoService;
	}

//	@GetMapping
//	public ResponseEntity<CollectionModel<LojaResource>> getAll() {
//		return new ResponseEntity<>(lojaResourceAssembler.toCollectionModel(lojaService.getAll()), HttpStatus.OK);
//	}
//
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
		MarcaProduto _marcaProduto = marcaProdutoService.getById(_produtoDTO.getMarcaId()).orElseThrow(() -> new EntityModelNotFoundException());
		CategoriaProduto _categoriaProduto = categoriaProdutoService.getById(_produtoDTO.getMarcaId()).orElseThrow(() -> new EntityModelNotFoundException());
		Produto _produto = new Produto();
		_produto.setMarca(_marcaProduto);
		_produto.setCategoria(_categoriaProduto);
		_produto.setMesesGarantia(_produtoDTO.getMesesGarantia());
		_produto.setDescricao(_produtoDTO.getDescricao());
		_produto.setNome(_produtoDTO.getNome());
		produtoService.save(_produto);
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
