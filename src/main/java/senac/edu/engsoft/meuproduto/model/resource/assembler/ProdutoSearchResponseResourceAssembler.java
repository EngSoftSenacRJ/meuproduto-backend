package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.SearchController;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.resource.ProdutoSearchResponseResource;

@Component
public class ProdutoSearchResponseResourceAssembler extends RepresentationModelAssemblerSupport<LojaProduto, ProdutoSearchResponseResource> {

	private static Class<SearchController> controllerClass = SearchController.class;

	public ProdutoSearchResponseResourceAssembler() {
		super(controllerClass, ProdutoSearchResponseResource.class);
	}

	@Override
	public ProdutoSearchResponseResource toModel(LojaProduto lojaProduto) {
		return instantiateModel(lojaProduto);
	}
	
	@Override
	protected ProdutoSearchResponseResource instantiateModel(LojaProduto lojaProduto) {
		return new ProdutoSearchResponseResource(lojaProduto);
	}

}
