package senac.edu.engsoft.meuproduto.model.resource;

import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;

public class MarcaProdutoResource extends RepresentationModel<MarcaProdutoResource> {

	private String nome;
	private String descricao;

	public MarcaProdutoResource() {
		super();
	}

	public MarcaProdutoResource(MarcaProduto marcaProduto) {
		this.nome = marcaProduto.getNome();
		this.descricao = marcaProduto.getDescricao();
	}
}
