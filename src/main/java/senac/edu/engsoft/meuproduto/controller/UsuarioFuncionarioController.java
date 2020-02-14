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
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioFuncionarioResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.UsuarioFuncionarioResourceAssembler;
import senac.edu.engsoft.meuproduto.service.UsuarioFuncionarioService;

@RestController
@RequestMapping({"/funcionarios"})
public class UsuarioFuncionarioController {

	private UsuarioFuncionarioService usuarioFuncionarioService;

	private final UsuarioFuncionarioResourceAssembler usuarioFuncionarioResourceAssembler;

	@Autowired
	public UsuarioFuncionarioController(UsuarioFuncionarioService usuarioFuncionarioService, UsuarioFuncionarioResourceAssembler usuarioFuncionarioResourceAssembler) {
		this.usuarioFuncionarioService = usuarioFuncionarioService;
		this.usuarioFuncionarioResourceAssembler = usuarioFuncionarioResourceAssembler;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping
	public CollectionModel<UsuarioFuncionarioResource> getAll() {
		CollectionModel<UsuarioFuncionarioResource> model = usuarioFuncionarioResourceAssembler.toCollectionModel(usuarioFuncionarioService.getAll());
		return model;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public UsuarioFuncionarioResource getById(@PathVariable Long id) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@GetMapping(value="/loja")
	public UsuarioFuncionarioResource getByNome(@RequestParam(value="nome", required=true) String nome) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	public UsuarioFuncionarioResource create(@RequestBody UsuarioFuncionario _usuarioFuncionario) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.saveOrUpdate(_usuarioFuncionario);
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@PutMapping
	public UsuarioFuncionarioResource update(@RequestBody UsuarioFuncionario _usuarioFuncionario) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.saveOrUpdate(_usuarioFuncionario);
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		usuarioFuncionarioService.delete(id);
	}
	
}
