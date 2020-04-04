package senac.edu.engsoft.meuproduto.model.resource.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.controller.LojaController;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.resource.LojaResource;

import java.util.ArrayList;
import java.util.List;

@Component
public class LojaResourceAssembler extends RepresentationModelAssemblerSupport<Loja, LojaResource> {
	
	private static Class<LojaController> controllerClass = LojaController.class;
	private boolean listarProdutos;


	public LojaResourceAssembler() {
		super(controllerClass, LojaResource.class);
	}

	@Override
	public LojaResource toModel(Loja loja) {
		return instantiateModel(loja);
	}

	@Override
	protected LojaResource instantiateModel(Loja loja) {
		return new LojaResource(loja, this.listarProdutos);
	}

	@Override
	public CollectionModel<LojaResource> toCollectionModel(Iterable<? extends Loja> entities) {
		List<LojaResource> lojaResources = new ArrayList<>();
		for(Loja loja : entities){
			lojaResources.add(toModel(loja));
		}
		CollectionModel<LojaResource> lojaResourceCollectionModel = new CollectionModel<>(lojaResources);
		return lojaResourceCollectionModel;
	}

	public CollectionModel<LojaResource> toCollectionModel(Iterable<Loja> lojas, boolean listarProdutos) {
		this.listarProdutos = listarProdutos;
		return toCollectionModel(lojas);
	}
}
