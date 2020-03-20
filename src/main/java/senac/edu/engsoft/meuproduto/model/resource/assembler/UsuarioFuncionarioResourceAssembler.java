package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.UsuarioFuncionarioController;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioFuncionarioResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioFuncionarioResourceAssembler extends RepresentationModelAssemblerSupport<UsuarioFuncionario, UsuarioFuncionarioResource> {
	
	private static Class<UsuarioFuncionarioController> usuarioFuncionarioClass = UsuarioFuncionarioController.class;

	public UsuarioFuncionarioResourceAssembler() {
		super(usuarioFuncionarioClass, UsuarioFuncionarioResource.class);
	}

	@Override
	public UsuarioFuncionarioResource toModel(UsuarioFuncionario usuarioFuncionario) {
		UsuarioFuncionarioResource usuarioFuncionarioResource = instantiateModel(usuarioFuncionario);
		usuarioFuncionarioResource.add(linkTo(methodOn(UsuarioFuncionarioController.class).delete(usuarioFuncionario.getId())).withRel("delete [DELETE]"));
		usuarioFuncionarioResource.add(linkTo(methodOn(UsuarioFuncionarioController.class).update(usuarioFuncionario, usuarioFuncionario.getId())).withRel("update [PUT]"));
		usuarioFuncionarioResource.add(linkTo(methodOn(UsuarioFuncionarioController.class).getById(usuarioFuncionario.getId())).withSelfRel());

		return usuarioFuncionarioResource;
	}
	
	@Override
	protected UsuarioFuncionarioResource instantiateModel(UsuarioFuncionario usuarioFuncionario) {
		return new UsuarioFuncionarioResource(usuarioFuncionario);
	}

}
