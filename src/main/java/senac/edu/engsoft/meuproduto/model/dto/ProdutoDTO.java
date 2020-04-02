package senac.edu.engsoft.meuproduto.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProdutoDTO {

	@JsonIgnore
	private Long id;
	@NotNull
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	@NotNull
	@NotBlank(message = "nome é obrigatório")
	private String descricao;
	@NotNull(message = "mesesGarantia é obrigatório")
	private Integer mesesGarantia;
	@NotNull(message = "marcaId é obrigatório")
	private Long marcaId;
	@NotNull(message = "categoriaId é obrigatório")
	private Long categoriaId;

}
