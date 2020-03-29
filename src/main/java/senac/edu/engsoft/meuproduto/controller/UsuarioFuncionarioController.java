package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioFuncionarioResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.UsuarioFuncionarioResourceAssembler;
import senac.edu.engsoft.meuproduto.service.UsuarioFuncionarioService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping({"/funcionarios"})
@Tag(name = "Usuário Funcionario", description = "Usuário Funcionário API")
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
	@Operation(summary = "Buscar Funcionários", description = "Buscar lista de usuários do tipo 'Funcionario'")
	public CollectionModel<UsuarioFuncionarioResource> getAll() {
		CollectionModel<UsuarioFuncionarioResource> model = usuarioFuncionarioResourceAssembler.toCollectionModel(usuarioFuncionarioService.getAll());
		return model;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/enabled")
	@Operation(summary = "Buscar Funcionarios habilitadas", description = "Buscar lista de usuários do tipo 'Funcionario' que estão habilitadas")
	public CollectionModel<UsuarioFuncionarioResource> getAllEnabled() {;
		CollectionModel<UsuarioFuncionarioResource> model = usuarioFuncionarioResourceAssembler.toCollectionModel(usuarioFuncionarioService.getAllEnabled());
		return model;
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar Funcionário por 'id'", description = "Buscar usuário do tipo 'Funcionario' por 'id")
	public UsuarioFuncionarioResource getById(@PathVariable(required = true) Long id) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@GetMapping(value="/loja")
	@Operation(summary = "Buscar Funcionário por 'nome'", description = "Buscar usuário do tipo 'Funcionario' por 'nome")
	public UsuarioFuncionarioResource getByNome(@RequestParam(value="nome", required=true) String nome) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getByNome(nome).orElseThrow(() -> new EntityModelNotFoundException());
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar Funcionário", description = "Criar usuário do tipo 'Funcionario'")
	public UsuarioFuncionarioResource create(@RequestBody @Valid UsuarioFuncionario _usuarioFuncionario) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.save(_usuarioFuncionario);
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	@Operation(summary = "Atualizar Funcionário por 'id'", description = "Atualizar usuário do tipo 'Funcionario' por 'id'")
	public UsuarioFuncionarioResource update(@RequestBody UsuarioFuncionario _usuarioFuncionario, @PathVariable Long id) {
		usuarioFuncionarioService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		UsuarioFuncionario usuarioFuncionario = null;
		usuarioFuncionario = usuarioFuncionarioService.update(id, _usuarioFuncionario);
		return usuarioFuncionarioResourceAssembler.toModel(usuarioFuncionario);
	}

	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}", produces="application/json")
	@Operation(summary = "Desabilitar Funcionário por 'id", description = "Desabilitar usuário do tipo 'Funcionario' por 'id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		usuarioFuncionario.setEnabled(false);
		usuarioFuncionarioService.update(id, usuarioFuncionario);
		return ResponseEntity.ok().build();
	}
	
}
