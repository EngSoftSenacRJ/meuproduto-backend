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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE", insertable = false, updatable = false)
	@JsonIgnore
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
	@Column(name="CEP_ENDERECO_PESSOAL")
	private String cepEnderecoPessoal;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="DATA_ANIVERSARIO")
	private LocalDate dataAniversario;

	@Column(name="DATA_CRIACAO")
	private LocalDate dataCriacao;
	
	@NotEmpty(message = "nome é obrigatório")
	@NotNull
	@Column(name="NOME")
	private String nome;
	
	@Column(name="TELEFONE_CONTATO")
	private Long telefoneContato;
	
	@NotNull
	@Column(name="CPF")
	private Long cpf;
	
	@NotEmpty(message = "email é obrigatório")
	@NotNull
	@Column(name="EMAIL")
	private String email;
	
	@JsonIgnore //TODO
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
	
	public Usuario(String ruaEnderecoPessoal, String numeroEnderecoPessoal,
			String bairroEnderecoPessoal, String cidadeEnderecoPessoal, String estadoEnderecoPessoal,
			String cepEnderecoPessoal, String nome,
			Long telefoneContato, Long cpf, String email,
			LocalDate dataAniversario) {
		this.ruaEnderecoPessoal = ruaEnderecoPessoal;
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
		this.cepEnderecoPessoal = cepEnderecoPessoal;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.email = email;
		this.dataAniversario = dataAniversario;
	}

	//Todos campos - id
	public Usuario(UsuarioType usuarioType, String ruaEnderecoPessoal, String numeroEnderecoPessoal,
			String bairroEnderecoPessoal, String cidadeEnderecoPessoal, String estadoEnderecoPessoal,
			String cepEnderecoPessoal, String senha, LocalDate dataCriacao, String nome,
			Long telefoneContato, Long cpf, String email, Set<Loja> lojas, LocalDate dataAniversario) {
		super();
		this.usuarioType = usuarioType;
		this.ruaEnderecoPessoal = ruaEnderecoPessoal;
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
		this.cepEnderecoPessoal = cepEnderecoPessoal;
		this.senha = senha;
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.email = email;
		this.lojas = lojas;
		this.dataAniversario = dataAniversario;
	}
	
}
