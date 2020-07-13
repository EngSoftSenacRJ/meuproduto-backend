package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "TB_MARCA_PRODUTO")
@Audited
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
	@Size(min = 2, max = 200)
	@Column(name="DESCRICAO")
	private String descricao;

	@OneToMany(mappedBy = "marca", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JsonIgnore
	private Set<Produto> produtoSet = new HashSet<>();

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
