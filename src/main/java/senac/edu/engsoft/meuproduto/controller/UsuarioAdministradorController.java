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
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioAdministradorResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.UsuarioAdministradorResourceAssembler;
import senac.edu.engsoft.meuproduto.service.UsuarioAdministradorService;

@RestController
@RequestMapping({"/funcionarios"})
public class UsuarioAdministradorController {

	private UsuarioAdministradorService usuarioAdministradorService;

	private final UsuarioAdministradorResourceAssembler usuarioAdministradorResourceAssembler;

	@Autowired
	public UsuarioAdministradorController(UsuarioAdministradorService usuarioAdministradorService, UsuarioAdministradorResourceAssembler usuarioAdministradorResourceAssembler) {
		this.usuarioAdministradorService = usuarioAdministradorService;
		this.usuarioAdministradorResourceAssembler = usuarioAdministradorResourceAssembler;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	public CollectionModel<UsuarioAdministradorResource> getAll() {
		CollectionModel<UsuarioAdministradorResource> model = usuarioAdministradorResourceAssembler.toCollectionModel(usuarioAdministradorService.getAll());
		return model;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public UsuarioAdministradorResource getById(@PathVariable Long id) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
	@GetMapping(value="/loja")
	public UsuarioAdministradorResource getByNome(@RequestParam(value="nome", required=true) String nome) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public UsuarioAdministradorResource create(@RequestBody UsuarioAdministrador _usuarioAdministrador) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.saveOrUpdate(_usuarioAdministrador);
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
	@PutMapping
	public UsuarioAdministradorResource update(@RequestBody UsuarioAdministrador _usuarioAdministrador) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.saveOrUpdate(_usuarioAdministrador);
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		usuarioAdministradorService.delete(id);
	}
	
}
