package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LojaProdutoDTO {

	@NotNull
	private Long idLoja;
	@NotNull
	private Long idProduto;
	@NotNull
	private Double preco;

}
