package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude
public class MarcaProdutoResource extends RepresentationModel<MarcaProdutoResource> {

	private Long id;
	private Boolean habilitado;
	private String nome;
	private String descricao;

	public MarcaProdutoResource(MarcaProduto marcaProduto) {
		this.id = marcaProduto.getId();
		this.habilitado = marcaProduto.getHabilitado();
		this.nome = marcaProduto.getNome();
		this.descricao = marcaProduto.getDescricao();
	}
}
