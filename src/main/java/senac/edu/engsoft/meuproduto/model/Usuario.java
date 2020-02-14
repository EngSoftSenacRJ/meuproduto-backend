package senac.edu.engsoft.meuproduto.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE", insertable = false, updatable = false)
	private UsuarioType usuarioType;
	
	@NotEmpty(message = "ruaEnderecoPessoal é obrigatório")
	@Column(name="RUA_ENDERECO_PESSOAL")
	private String ruaEnderecoPessoal;
	
	@NotEmpty(message = "numeroEnderecoPessoal é obrigatório")
	@Column(name="NUMERO_ENDERECO_PESSOAL")
	private String numeroEnderecoPessoal;
	
	@NotEmpty(message = "bairroEnderecoPessoal é obrigatório")
	@Column(name="BAIRRO_ENDERECO_PESSOAL")
	private String bairroEnderecoPessoal;
	
	@NotEmpty(message = "cidadeEnderecoPessoal é obrigatório")
	@Column(name="CIDADE_ENDERECO_PESSOAL")
	private String cidadeEnderecoPessoal;
	
	@NotEmpty(message = "estadoEnderecoPessoal é obrigatório")
	@Column(name="ESTADO_ENDERECO_PESSOAL")
	private String estadoEnderecoPessoal;
	
	@NotEmpty(message = "cepEnderecoPessoal é obrigatório")
	@Column(name="CE_ENDERECO_PESSOAL")
	private String cepEnderecoPessoal;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="SALT")
	private String salt;
	
	@Column(name="DATA_CRIACAO")
	private LocalDate dataCriacao;
	
	@NotEmpty(message = "nome é obrigatório")
	@NotNull
	@Column(name="NOME")
	private String nome;
	
	@Column(name="TELEFONE_CONTATO")
	private Long telefoneContato;
	
//	@NotEmpty(message = "cpf é obrigatório")
	@NotNull
	@Column(name="CPF")
	private Long cpf;
	
	@NotEmpty(message = "email é obrigatório")
	@NotNull
	@Column(name="EMAIL")
	private String email;
	
	@JoinColumn(name = "ID_PERFIL")
	@ManyToOne(fetch = FetchType.LAZY)
	private Perfil perfil;
	
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_LOJA",
		joinColumns = { @JoinColumn(name = "ID_USUARIO") },
		inverseJoinColumns = { @JoinColumn(name = "ID_LOJA") })
	private Set<Loja> lojas = new HashSet<>();
	
	@PrePersist
	private void prePersist() {
		if(this.getDataCriacao() == null)
			this.setDataCriacao(LocalDate.now());
	}
	
	public Usuario() {
		super();
	}

	//Todos campos - id
	public Usuario(UsuarioType usuarioType, String ruaEnderecoPessoal, String numeroEnderecoPessoal,
			String bairroEnderecoPessoal, String cidadeEnderecoPessoal, String estadoEnderecoPessoal,
			String cepEnderecoPessoal, String password, String salt, LocalDate dataCriacao, String nome,
			Long telefoneContato, Long cpf, String email, Perfil perfil, Set<Loja> lojas) {
		super();
		this.usuarioType = usuarioType;
		this.ruaEnderecoPessoal = ruaEnderecoPessoal;
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
		this.cepEnderecoPessoal = cepEnderecoPessoal;
		this.password = password;
		this.salt = salt;
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.email = email;
		this.perfil = perfil;
		this.lojas = lojas;
	}
	
}
