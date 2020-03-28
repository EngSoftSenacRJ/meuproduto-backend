package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Entity
@Table(name = "TB_MARCA_PRODUTO")
public class MarcaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="HABILITADO")
	@JsonIgnore
	private Boolean habilitado;

	@NotEmpty(message = "nome é obrigatório")
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name="NOME")
	private String nome;

	@NotEmpty(message = "descricao é obrigatória")
	@NotNull
	@Size(min = 5, max = 200)
	@Column(name="DESCRICAO")
	private String descricao;

	@PrePersist
	public void prePersist(){
		if(this.getHabilitado() == null)
			setHabilitado(true);
	}

	public MarcaProduto() {
		super();
	}

	public MarcaProduto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

    public void copyForNew(MarcaProduto other) {
		this.setHabilitado(true);

		if(other.getNome() != null)
			this.setNome(other.getNome());
		if(other.getDescricao() != null)
			this.setDescricao(other.getDescricao());
    }
}
