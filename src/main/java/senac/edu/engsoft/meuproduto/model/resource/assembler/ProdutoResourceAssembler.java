package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.ProdutoController;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.resource.ProdutoResource;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoResourceAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoResource> {

	private static Class<ProdutoController> controllerClass = ProdutoController.class;

	public ProdutoResourceAssembler() {
		super(controllerClass, ProdutoResource.class);
	}

	@Override
	public ProdutoResource toModel(Produto produto) {
		return instantiateModel(produto);
	}
	
	@Override
	protected ProdutoResource instantiateModel(Produto produto) {
		return new ProdutoResource(produto, true);
	}

	@Override
	public CollectionModel<ProdutoResource> toCollectionModel(Iterable<? extends Produto> entities) {
		List<ProdutoResource> produtoResources = new ArrayList<>();
		for(Produto produto : entities){
			produtoResources.add(toModel(produto));
		}
		CollectionModel<ProdutoResource> produtoResourceCollectionModel = new CollectionModel<>(produtoResources);
		return produtoResourceCollectionModel;
	}

}
