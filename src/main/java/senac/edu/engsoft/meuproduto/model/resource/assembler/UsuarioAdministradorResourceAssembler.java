package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import senac.edu.engsoft.meuproduto.controller.UsuarioAdministradorController;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioAdministradorResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioAdministradorResourceAssembler extends RepresentationModelAssemblerSupport<UsuarioAdministrador, UsuarioAdministradorResource> {
	
	private static Class<UsuarioAdministradorController> usuarioAdministradorClass = UsuarioAdministradorController.class;

	public UsuarioAdministradorResourceAssembler() {
		super(usuarioAdministradorClass, UsuarioAdministradorResource.class);
	}

	@Override
	public UsuarioAdministradorResource toModel(UsuarioAdministrador usuarioAdministrador) {
		UsuarioAdministradorResource usuarioAdministradorResource = instantiateModel(usuarioAdministrador);
		usuarioAdministradorResource.add(linkTo(methodOn(UsuarioAdministradorController.class).delete(usuarioAdministrador.getId())).withRel("delete [DELETE]"));
		usuarioAdministradorResource.add(linkTo(methodOn(UsuarioAdministradorController.class).update(usuarioAdministrador)).withRel("update [PUT]"));
		usuarioAdministradorResource.add(linkTo(methodOn(UsuarioAdministradorController.class).getById(usuarioAdministrador.getId())).withSelfRel());
		
		return usuarioAdministradorResource;
	}
	
	@Override
	protected UsuarioAdministradorResource instantiateModel(UsuarioAdministrador usuarioAdministrador) {
		return new UsuarioAdministradorResource(
				usuarioAdministrador.getCpf(), 
				usuarioAdministrador.getNome(), 
				usuarioAdministrador.getTelefoneContato(), 
				usuarioAdministrador.getEmail(),
				usuarioAdministrador.getNumeroEnderecoPessoal(), 
				usuarioAdministrador.getBairroEnderecoPessoal(), 
				usuarioAdministrador.getCidadeEnderecoPessoal(), 
				usuarioAdministrador.getEstadoEnderecoPessoal(), 
				usuarioAdministrador.getCepEnderecoPessoal());
	}

}
