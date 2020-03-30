package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Integer mesesGarantia;
	private Long marcaId;
	private Long categoriaId;

}
