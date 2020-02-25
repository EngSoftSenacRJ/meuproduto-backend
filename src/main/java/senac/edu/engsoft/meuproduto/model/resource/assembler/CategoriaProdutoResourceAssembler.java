package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.CategoriaProdutoController;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.model.resource.CategoriaProdutoResource;

@Component
public class CategoriaProdutoResourceAssembler extends RepresentationModelAssemblerSupport<CategoriaProduto, CategoriaProdutoResource> {

	private static Class<CategoriaProdutoController> controllerClass = CategoriaProdutoController.class;

	public CategoriaProdutoResourceAssembler() {
		super(controllerClass, CategoriaProdutoResource.class);
	}

	@Override
	public CategoriaProdutoResource toModel(CategoriaProduto categoriaProduto) {
		return instantiateModel(categoriaProduto);
	}
	
	@Override
	protected CategoriaProdutoResource instantiateModel(CategoriaProduto categoriaProduto) {
		return new CategoriaProdutoResource(categoriaProduto);
	}

}
