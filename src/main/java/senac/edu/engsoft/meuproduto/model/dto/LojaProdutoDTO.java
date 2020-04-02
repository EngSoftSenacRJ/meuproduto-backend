package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LojaProdutoDTO {

	@NotNull
	@NotBlank(message = "idLoja é obrigatório")
	private Long idLoja;
	@NotNull
	@NotBlank(message = "idProduto é obrigatório")
	private Long idProduto;
	@NotNull
	@NotBlank(message = "preco é obrgatório")
	private Double preco;

}
