package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import senac.edu.engsoft.meuproduto.controller.UsuarioAdministradorController;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioAdministradorResource;

@Component
public class UsuarioAdministradorResourceAssembler extends RepresentationModelAssemblerSupport<UsuarioAdministrador, UsuarioAdministradorResource> {
	
	private static Class<UsuarioAdministradorController> usuarioAdministradorClass = UsuarioAdministradorController.class;

	public UsuarioAdministradorResourceAssembler() {
		super(usuarioAdministradorClass, UsuarioAdministradorResource.class);
	}

	@Override
	public UsuarioAdministradorResource toModel(UsuarioAdministrador usuarioAdministrador) {
		return createModelWithId(usuarioAdministrador.getId(), usuarioAdministrador);
	}
	
	@Override
	protected UsuarioAdministradorResource instantiateModel(UsuarioAdministrador usuarioAdministrador) {
		return new UsuarioAdministradorResource(usuarioAdministrador.getDataCriacao(), 
				usuarioAdministrador.getNome(), 
				usuarioAdministrador.getTelefoneContato(), 
				usuarioAdministrador.getCpf(), 
				usuarioAdministrador.getPerfil().getNome());
	}

}
