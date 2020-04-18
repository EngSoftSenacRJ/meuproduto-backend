package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.LojaProduto;

@Getter
@Setter
@JsonInclude
public class ProdutoSearchResponseResource extends RepresentationModel<ProdutoSearchResponseResource> {

	private Long id;
	private String nome;
	private String descricao;
	private Integer mesesGarantia;
	private MarcaProdutoResource marca;
	private CategoriaProdutoResource categoria;
	private Double preco;
	private LojaResource lojaResource;

	public ProdutoSearchResponseResource() {
		super();
	}

	public ProdutoSearchResponseResource(LojaProduto lojaProduto) {
		this.id = lojaProduto.getProduto().getId();
		this.nome = lojaProduto.getProduto().getNome();
		this.descricao = lojaProduto.getProduto().getDescricao();
		this.mesesGarantia = lojaProduto.getProduto().getMesesGarantia();
		this.setMarca(new MarcaProdutoResource(lojaProduto.getProduto().getMarca()));
		this.setCategoria(new CategoriaProdutoResource(lojaProduto.getProduto().getCategoria()));
		this.preco = lojaProduto.getPreco();
		this.lojaResource =new LojaResource(lojaProduto.getLoja(), false);
	}

}
