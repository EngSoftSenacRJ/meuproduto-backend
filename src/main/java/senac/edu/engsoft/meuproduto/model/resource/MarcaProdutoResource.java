package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;

@Getter
@Setter
@JsonInclude
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
