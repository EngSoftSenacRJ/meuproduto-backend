package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Data
@Getter
@Setter
@Entity
@Table(name = "TB_PRODUTO")
@Indexed
public class Produto extends BaseIndexedEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	@JsonIgnore
	private Long id;

	@NotNull(message = "Nome é obrigatório")
	@NotEmpty(message = "Nome é obrigatório")
	@Size(min = 3, max = 50, message = "Nome do produto deve ter no mínimo '3' e no máximo '50' caracteres")
	@Field
	@Analyzer(definition = "customanalyzer")
	@Column(name = "NOME")
	private String nome;

	@Size(min = 0, max = 100, message = "Descrição do produto deve ter no máximo '50' caracteres")
	@Field
	@Analyzer(definition = "customanalyzer")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "IMAGEM")
	private String imagemBase64;

	@NotNull
	@Min(value = 1, message = "Meses de Garantia do produto deve ter no mínimo '1' dígito")
	@Column(name="MESES_GARANTIA")
	private Integer mesesGarantia;

	@NotNull(message = "Marca é obrigatório")
//	@Field
//	@IndexedEmbedded
	@JsonBackReference
	@JoinColumn(name = "ID_MARCA")
	@ManyToOne(fetch = FetchType.LAZY)
	private MarcaProduto marca;

	@NotNull(message = "Categoria é obrigatório")
//	@Field
//	@IndexedEmbedded
	@JsonBackReference
	@JoinColumn(name = "ID_CATEGORIA")
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoriaProduto categoria;

	@JsonIgnore
	@IndexedEmbedded
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<LojaProduto> lojaProdutoSet = new HashSet<>();

	public void copyForNew(Produto other) {
		if(other.getNome() != null)
			this.setNome(other.getNome());
		if(other.getDescricao() != null)
			this.setDescricao(other.getDescricao());
		if(other.getMesesGarantia() != null)
			this.setMesesGarantia(other.getMesesGarantia());
		if(other.getMarca() != null)
			this.setMarca(other.getMarca());
		if(other.getCategoria() != null)
			this.setCategoria(other.getCategoria());
		//TODO: lojaProdutoSet
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
