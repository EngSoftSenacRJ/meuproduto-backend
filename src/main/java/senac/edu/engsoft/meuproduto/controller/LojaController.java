package senac.edu.engsoft.meuproduto.controller;

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

@CrossOrigin
@RestController
@RequestMapping({"/lojas"})
public class LojaController {

	private LojaService lojaService;
	private final LojaResourceAssembler lojaResourceAssembler;

	@Autowired
	public LojaController(LojaService lojaService, LojaResourceAssembler lojaResourceAssembler) {
		this.lojaService = lojaService;
		this.lojaResourceAssembler = lojaResourceAssembler;
	}
	
//	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	public ResponseEntity<CollectionModel<LojaResource>> getAll() {
		return new ResponseEntity<>(lojaResourceAssembler.toCollectionModel(lojaService.getAll()), HttpStatus.OK);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public LojaResource getById(@PathVariable Long id) {
		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return lojaResourceAssembler.toModel(loja);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value="/loja")
	public LojaResource getByNome(@RequestParam(value="nome") String nome) {
		Loja loja = lojaService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return lojaResourceAssembler.toModel(loja);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid Loja _loja) {
		Loja loja = lojaService.save(_loja);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping
	public LojaResource update(@RequestBody @Valid Loja _loja) {
		Loja loja = lojaService.update(_loja);
		return lojaResourceAssembler.toModel(loja);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		lojaService.delete(loja.getId());
		return ResponseEntity.noContent().build();
	}
	
}
