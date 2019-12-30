package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import senac.edu.engsoft.meuproduto.controller.LojaController;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.resource.LojaResource;

@Component
public class LojaResourceAssembler extends RepresentationModelAssemblerSupport<Loja, LojaResource> {
	
	private static Class<LojaController> controllerClass = LojaController.class;

	public LojaResourceAssembler() {
		super(controllerClass, LojaResource.class);
	}

	@Override
	public LojaResource toModel(Loja loja) {
		return createModelWithId(loja.getId(), loja);
	}
	
	@Override
	protected LojaResource instantiateModel(Loja loja) {
		StringBuilder sbEnderecoCompleto = new StringBuilder();
		if(loja.getRuaEnderecoComercial() != null && loja.getRuaEnderecoComercial().trim().length() > 0) {
			sbEnderecoCompleto.append("Rua ");
			sbEnderecoCompleto.append(loja.getRuaEnderecoComercial());
		}
		if(loja.getNumeroEnderecoComercial() != null && loja.getNumeroEnderecoComercial().trim().length() > 0) {
			sbEnderecoCompleto.append(", Número ");
			sbEnderecoCompleto.append(loja.getNumeroEnderecoComercial());
		}
		if(loja.getBairroEnderecoComercial() != null && loja.getBairroEnderecoComercial().trim().length() > 0) {
			sbEnderecoCompleto.append(", Bairro ");
			sbEnderecoCompleto.append(loja.getBairroEnderecoComercial());
		}
		
		return new LojaResource(loja.getNome(), sbEnderecoCompleto.toString());
	}

}
