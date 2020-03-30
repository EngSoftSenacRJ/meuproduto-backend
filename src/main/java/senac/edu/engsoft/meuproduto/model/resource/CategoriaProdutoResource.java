package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;

@Getter
@Setter
@JsonInclude
public class CategoriaProdutoResource extends RepresentationModel<CategoriaProdutoResource> {

	private Long id;
	private String nome;
	private String descricao;

	public CategoriaProdutoResource() {
		super();
	}

	public CategoriaProdutoResource(CategoriaProduto categoriaProduto) {
		this.id = categoriaProduto.getId();
		this.nome = categoriaProduto.getNome();
		this.descricao = categoriaProduto.getDescricao();
	}
}
