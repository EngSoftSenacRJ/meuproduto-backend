package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_CATEGORIA_PRODUTO")
@Audited
public class CategoriaProduto extends BaseIndexedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Column(name="DESCRICAO")
	private String descricao;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JsonIgnore
	private Set<Produto> produtoSet = new HashSet<>();

	public CategoriaProduto() {
		super();
	}

	public CategoriaProduto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

}
