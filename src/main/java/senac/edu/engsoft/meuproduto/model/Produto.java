package senac.edu.engsoft.meuproduto.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Getter
@Setter
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Nome é obrigatório")
	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "NOME")
	private String nome;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name="MESES_GARANTIA")
	private Integer mesesGarantia;

	@NotNull(message = "Marca é obrigatório")
	@NotBlank(message = "Marca é obrigatório")
	@Column(name="MARCA")
	private String marca;

	@NotNull(message = "Categoria é obrigatório")
	@NotBlank(message = "Categoria é obrigatório")
	@JoinColumn(name = "ID_CATEGORIA")
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<LojaProduto> lojaProdutoSet = new HashSet<>();

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
