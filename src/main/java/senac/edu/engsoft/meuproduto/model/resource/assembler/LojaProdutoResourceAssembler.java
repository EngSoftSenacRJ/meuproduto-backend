//package senac.edu.engsoft.meuproduto.model.resource.assembler;
//
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
//import org.springframework.stereotype.Component;
//import senac.edu.engsoft.meuproduto.controller.LojaProdutoController;
//import senac.edu.engsoft.meuproduto.model.Loja;
//import senac.edu.engsoft.meuproduto.model.LojaProduto;
//import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoResource;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class LojaProdutoResourceAssembler extends RepresentationModelAssemblerSupport<LojaProduto, LojaProdutoResource> {
//
//	private static Class<LojaProdutoController> controllerClass = LojaProdutoController.class;
//
//	public LojaProdutoResourceAssembler() {
//		super(controllerClass, LojaProdutoResource.class);
//	}
//
//	@Override
//	public LojaProdutoResource toModel(LojaProduto lojaProduto) {
//		return instantiateModel(lojaProduto);
//	}
//
//	@Override
//	protected LojaProdutoResource instantiateModel(LojaProduto lojaProduto) {
//		return new LojaProdutoResource(lojaProduto);
//	}
//
//	@Override
//	public CollectionModel<LojaProdutoResource> toCollectionModel(Iterable<? extends LojaProduto> entities) {
//		List<LojaProdutoResource> lojaProdutoResources = new ArrayList<>();
//		for(LojaProduto lojaProduto : entities){
//			lojaProdutoResources.add(toModel(lojaProduto));
//		}
//		CollectionModel<LojaProdutoResource> lojaProdutoResourceCollectionModel = new CollectionModel<>(lojaProdutoResources);
//		return lojaProdutoResourceCollectionModel;
//	}
//
//}
