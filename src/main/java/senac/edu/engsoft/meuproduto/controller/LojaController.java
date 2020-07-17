package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.resource.LojaResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.LojaResourceAssembler;
import senac.edu.engsoft.meuproduto.service.LojaService;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping({"/lojas"})
@Tag(name = "Loja", description = "Loja API")
public class LojaController {

	private LojaService lojaService;
	private final LojaResourceAssembler lojaResourceAssembler;

	@Autowired
	public LojaController(LojaService lojaService, LojaResourceAssembler lojaResourceAssembler) {
		this.lojaService = lojaService;
		this.lojaResourceAssembler = lojaResourceAssembler;
	}


	@GetMapping
	@Operation(summary = "Buscar Lojas", description = "Buscar lista de Lojas")
	public ResponseEntity<CollectionModel<LojaResource>> getAll(@RequestParam("listarProdutos") Optional<Boolean> listarProdutos, @RequestParam(value="usernameAdministrador", required=true) String usernameAdministrador) {
		return new ResponseEntity<>(lojaResourceAssembler.toCollectionModel(lojaService.getLojasByUsernameAdministrador(usernameAdministrador), listarProdutos.isPresent() ? listarProdutos.get() : false), HttpStatus.OK);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar Loja por 'id'", description = "Buscar Loja por 'id'")
	public LojaResource getById(@PathVariable Long id) {
		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return lojaResourceAssembler.toModel(loja);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value="/loja")
	@Operation(summary = "Buscar Loja por 'nome'", description = "Buscar Loja por 'nome'")
	public LojaResource getByNome(@RequestParam(value="nome") String nome) {
		Loja loja = lojaService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return lojaResourceAssembler.toModel(loja);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar Loja", description = "Criar Loja")
	public ResponseEntity<Object> create(@RequestBody @Valid Loja _loja) {
		Loja loja = lojaService.save(_loja);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	@Operation(summary = "Atualizar Loja por 'id'", description = "Atualizar Loja por 'id'")
	public LojaResource update(@RequestBody Loja _loja, @PathVariable Long id) {
		Loja lojaAtual = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		Loja loja = lojaService.update(id, _loja, lojaAtual);
		return lojaResourceAssembler.toModel(loja);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remover Loja por 'id'", description = "Remover Loja por 'id'")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		lojaService.delete(loja.getId());
		return ResponseEntity.noContent().build();
	}
	
}
