package senac.edu.engsoft.meuproduto.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "TB_MARCA_PRODUTO")
public class MarcaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name="NOME")
	private String nome;

	@NotEmpty
	@NotNull
	@Size(min = 5, max = 200)
	@Column(name="DESCRICAO")
	private String descricao;

	public MarcaProduto() {
		super();
	}

	public MarcaProduto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

}
