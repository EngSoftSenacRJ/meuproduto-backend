package senac.edu.engsoft.meuproduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.advice.exception.AdministratorUserFailedDeleteException;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioAdministradorResource;
import senac.edu.engsoft.meuproduto.model.resource.assembler.UsuarioAdministradorResourceAssembler;
import senac.edu.engsoft.meuproduto.service.UsuarioAdministradorService;

@CrossOrigin
@RestController
@RequestMapping({"/administradores"})
public class UsuarioAdministradorController {

	private UsuarioAdministradorService usuarioAdministradorService;
	private final UsuarioAdministradorResourceAssembler usuarioAdministradorResourceAssembler;

	@Autowired
	public UsuarioAdministradorController(UsuarioAdministradorService usuarioAdministradorService,
										  UsuarioAdministradorResourceAssembler usuarioAdministradorResourceAssembler
										  ) {
		this.usuarioAdministradorService = usuarioAdministradorService;
		this.usuarioAdministradorResourceAssembler = usuarioAdministradorResourceAssembler;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(produces="application/json")
	public CollectionModel<UsuarioAdministradorResource> getAll() {
		return usuarioAdministradorResourceAssembler.toCollectionModel(usuarioAdministradorService.getAll());
	}

	@ResponseStatus(value=HttpStatus.OK)
	@GetMapping(value = "/{id}", produces="application/json")
	public UsuarioAdministradorResource getById(@PathVariable Long id) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}

	@GetMapping(value="/administrador", produces="application/json")
	public UsuarioAdministradorResource getByUsername(@RequestParam(value="username") String username) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.getByUsername(username).orElseThrow(() -> new EntityModelNotFoundException());
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
//	@ResponseStatus(value=HttpStatus.CREATED)
//	@PostMapping(produces="application/json", consumes="application/json")
//	public ResponseEntity<Void> create(@RequestBody UsuarioAdministrador _usuarioAdministrador) {
//		_usuarioAdministrador.setSenha(bCryptPasswordEncoder.encode(_usuarioAdministrador.getSenha()));
//		usuarioAdministradorService.save(_usuarioAdministrador);
//		return ResponseEntity.ok().build();
//	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	public UsuarioAdministradorResource update(@RequestBody UsuarioAdministrador _usuarioAdministrador, @PathVariable Long id) {
		usuarioAdministradorService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.update(id, _usuarioAdministrador);
		return usuarioAdministradorResourceAssembler.toModel(usuarioAdministrador);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.getById(id).orElseThrow(() -> new EntityModelNotFoundException(id));
		usuarioAdministrador.setEnabled(false);
		try {
			usuarioAdministradorService.update(id, usuarioAdministrador);
		}catch (AdministratorUserFailedDeleteException e){
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().build();
	}
	
}
