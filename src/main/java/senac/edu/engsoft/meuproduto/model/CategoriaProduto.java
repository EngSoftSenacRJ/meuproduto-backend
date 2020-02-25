package senac.edu.engsoft.meuproduto.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_CATEGORIA_PRODUTO")
public class CategoriaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME")
	private String nome;

	@Column(name="DESCRICAO")
	private String descricao;
	
	public CategoriaProduto() {
		super();
	}

	public CategoriaProduto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

}
