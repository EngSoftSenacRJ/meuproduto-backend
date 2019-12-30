package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import senac.edu.engsoft.meuproduto.controller.UsuarioFuncionarioController;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.model.resource.UsuarioFuncionarioResource;

@Component
public class UsuarioFuncionarioResourceAssembler extends RepresentationModelAssemblerSupport<UsuarioFuncionario, UsuarioFuncionarioResource> {
	
	private static Class<UsuarioFuncionarioController> usuarioFuncionarioClass = UsuarioFuncionarioController.class;

	public UsuarioFuncionarioResourceAssembler() {
		super(usuarioFuncionarioClass, UsuarioFuncionarioResource.class);
	}

	@Override
	public UsuarioFuncionarioResource toModel(UsuarioFuncionario usuarioFuncionario) {
		return createModelWithId(usuarioFuncionario.getId(), usuarioFuncionario);
	}
	
	@Override
	protected UsuarioFuncionarioResource instantiateModel(UsuarioFuncionario usuarioFuncionario) {
		return new UsuarioFuncionarioResource(usuarioFuncionario.getDataCriacao(), 
				usuarioFuncionario.getNome(), 
				usuarioFuncionario.getTelefoneContato(), 
				usuarioFuncionario.getCpf(), 
				usuarioFuncionario.getPerfil().getNome());
	}

}
