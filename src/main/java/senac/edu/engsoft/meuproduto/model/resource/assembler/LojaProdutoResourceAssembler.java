package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.LojaProdutoController;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoResource;

import java.util.ArrayList;
import java.util.List;

@Component
public class LojaProdutoResourceAssembler extends RepresentationModelAssemblerSupport<Loja, LojaProdutoResource> {

	private static Class<LojaProdutoController> controllerClass = LojaProdutoController.class;

	public LojaProdutoResourceAssembler() {
		super(controllerClass, LojaProdutoResource.class);
	}

	@Override
	public LojaProdutoResource toModel(Loja loja) {
		return instantiateModel(loja);
	}
	
	@Override
	protected LojaProdutoResource instantiateModel(Loja loja) {
		return new LojaProdutoResource(loja);
	}

	@Override
	public CollectionModel<LojaProdutoResource> toCollectionModel(Iterable<? extends Loja> entities) {
		List<LojaProdutoResource> lojaProdutoResources = new ArrayList<>();
		for(Loja loja : entities){
			lojaProdutoResources.add(toModel(loja));
		}
		CollectionModel<LojaProdutoResource> lojaProdutoResourceCollectionModel = new CollectionModel<>(lojaProdutoResources);
		return lojaProdutoResourceCollectionModel;
	}

}
