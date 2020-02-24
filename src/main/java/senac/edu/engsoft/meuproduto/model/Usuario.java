package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@JsonInclude
public class Usuario implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
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
	@Column(name="CEP_ENDERECO_PESSOAL")
	private String cepEnderecoPessoal;

	@Column(name="DATA_ANIVERSARIO")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataAniversario;

	@Column(name="DATA_CRIACAO")
	@JsonIgnore
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

	/*
		Spring security Fields
	 */
	@Column(name="SENHA")
	private String password;

	@NotEmpty(message = "email é obrigatório")
	@NotNull
	@Column(name="EMAIL")
	private String username;

	@Column(name="HABILITADO")
	@JsonIgnore
	private boolean enabled;
	/*
		Spring security Fields
	 */

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_LOJA",
		joinColumns = { @JoinColumn(name = "ID_USUARIO") },
		inverseJoinColumns = { @JoinColumn(name = "ID_LOJA") })
	private Set<Loja> lojas = new HashSet<>();

	public Usuario(Usuario usuario) {
		this.ruaEnderecoPessoal = usuario.getRuaEnderecoPessoal();
		this.numeroEnderecoPessoal = usuario.getNumeroEnderecoPessoal();
		this.bairroEnderecoPessoal = usuario.getBairroEnderecoPessoal();
		this.cidadeEnderecoPessoal = usuario.getCidadeEnderecoPessoal();
		this.estadoEnderecoPessoal = usuario.getEstadoEnderecoPessoal();
		this.cepEnderecoPessoal = usuario.getCepEnderecoPessoal();
		this.nome = usuario.getNome();
		this.telefoneContato = usuario.getTelefoneContato();
		this.cpf = usuario.getCpf();
		this.username = usuario.getUsername();
		this.dataAniversario = usuario.getDataAniversario();
		this.password = usuario.getPassword();
		this.enabled = true;
	}



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
			LocalDate dataAniversario, boolean desabilitado) {
		this.ruaEnderecoPessoal = ruaEnderecoPessoal;
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
		this.cepEnderecoPessoal = cepEnderecoPessoal;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.username = email;
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
		this.password = senha;
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.username = email;
		this.lojas = lojas;
		this.dataAniversario = dataAniversario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
