package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.Produto;

@Getter
@Setter
@JsonInclude
public class ProdutoResource extends RepresentationModel<ProdutoResource> {

	private Long id;
	private String nome;
	private String descricao;
	private Integer mesesGarantia;
	private MarcaProdutoResource marca;
	private CategoriaProdutoResource categoria;

	public ProdutoResource() {
		super();
	}

	public ProdutoResource(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.mesesGarantia = produto.getMesesGarantia();
		this.setMarca(new MarcaProdutoResource(produto.getMarca()));
		this.setCategoria(new CategoriaProdutoResource(produto.getCategoria()));
	}

}
