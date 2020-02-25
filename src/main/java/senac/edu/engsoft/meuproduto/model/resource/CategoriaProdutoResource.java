package senac.edu.engsoft.meuproduto.model.resource;

import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;

public class CategoriaProdutoResource extends RepresentationModel<CategoriaProdutoResource> {

	private String nome;
	private String descricao;

	public CategoriaProdutoResource() {
		super();
	}

	public CategoriaProdutoResource(CategoriaProduto categoriaProduto) {
		this.nome = categoriaProduto.getNome();
		this.descricao = categoriaProduto.getDescricao();
	}
}
