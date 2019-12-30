package senac.edu.engsoft.meuproduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.resource.LojaResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.LojaResourceAssembler;
import senac.edu.engsoft.meuproduto.service.LojaService;

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
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	public CollectionModel<LojaResource> getAll() {
		CollectionModel<LojaResource> model = lojaResourceAssembler.toCollectionModel(lojaService.getAll());
		return model;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public LojaResource getById(@PathVariable Long id) {
		Loja loja = lojaService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return lojaResourceAssembler.toModel(loja);
	}
	
	@GetMapping(value="/loja")
	public LojaResource getByNome(@RequestParam(value="nome", required=true) String nome) {
		Loja loja = lojaService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return lojaResourceAssembler.toModel(loja);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public LojaResource create(@RequestBody Loja _loja) {
		Loja loja = lojaService.saveOrUpdate(_loja);
		return lojaResourceAssembler.toModel(loja);
	}
	
	@PutMapping
	public LojaResource update(@RequestBody Loja _loja) {
		Loja loja = lojaService.saveOrUpdate(_loja);
		return lojaResourceAssembler.toModel(loja);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		lojaService.delete(id);
	}
	
}
