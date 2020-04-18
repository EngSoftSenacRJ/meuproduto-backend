package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;

import java.util.HashSet;
import java.util.Set;

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
	private Set<LojaProdutoResource> lojaProdutoResourceSet;

	public ProdutoResource() {
		super();
	}

	public ProdutoResource(Produto produto, boolean loadLoja) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.mesesGarantia = produto.getMesesGarantia();
		this.setMarca(new MarcaProdutoResource(produto.getMarca()));
		this.setCategoria(new CategoriaProdutoResource(produto.getCategoria()));
		if(loadLoja) {
			this.setLojaProdutoResourceSet(new HashSet<>());
			for (LojaProduto lojaProduto : produto.getLojaProdutoSet()) {
				this.getLojaProdutoResourceSet().add(new LojaProdutoResource(lojaProduto, true, false));
			}
		}
	}

}
