package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.MarcaProdutoController;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.model.resource.MarcaProdutoResource;

@Component
public class MarcaProdutoResourceAssembler extends RepresentationModelAssemblerSupport<MarcaProduto, MarcaProdutoResource> {

	private static Class<MarcaProdutoController> controllerClass = MarcaProdutoController.class;

	public MarcaProdutoResourceAssembler() {
		super(controllerClass, MarcaProdutoResource.class);
	}

	@Override
	public MarcaProdutoResource toModel(MarcaProduto marcaProduto) {
		return instantiateModel(marcaProduto);
	}
	
	@Override
	protected MarcaProdutoResource instantiateModel(MarcaProduto marcaProduto) {
		return new MarcaProdutoResource(marcaProduto);
	}

}
