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
		return instantiateModel(loja);
	}
	
	@Override
	protected LojaResource instantiateModel(Loja loja) {
		return new LojaResource(loja);
	}

}
