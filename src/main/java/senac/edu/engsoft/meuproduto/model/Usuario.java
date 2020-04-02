package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import senac.edu.engsoft.meuproduto.model.json.validator.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.*;
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
	@Size(min = 3, max = 50, message = "Rua Endereço Pessoal deve ter no mínimo '3' e no máximo '50' caracteres")
	private String ruaEnderecoPessoal;
	
	@NotEmpty(message = "numeroEnderecoPessoal é obrigatório")
	@Pattern(regexp = "[0-9]{1,10}", message = "Número Endereço Pessoal deve ter no mínimo '1' e no máximo '10' dígitos")
	@Column(name="NUMERO_ENDERECO_PESSOAL")
	private String numeroEnderecoPessoal;
	
	@NotEmpty(message = "bairroEnderecoPessoal é obrigatório")
	@Size(min = 3, max = 50, message = "Bairro Endereço Pessoal deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="BAIRRO_ENDERECO_PESSOAL")
	private String bairroEnderecoPessoal;
	
	@NotEmpty(message = "cidadeEnderecoPessoal é obrigatório")
	@Size(min = 3, max = 50, message = "Cidade Endereço Pessoal deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="CIDADE_ENDERECO_PESSOAL")
	private String cidadeEnderecoPessoal;
	
	@NotEmpty(message = "estadoEnderecoPessoal é obrigatório")
	@Size(min = 3, max = 50, message = "Estado Endereço Pessoal deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="ESTADO_ENDERECO_PESSOAL")
	private String estadoEnderecoPessoal;
	
	@NotEmpty(message = "cepEnderecoPessoal é obrigatório")
	@Pattern(regexp = "[0-9]{8}", message = "CEP Endereço Pessoal deve ter '8' dígitos")
	@Column(name="CEP_ENDERECO_PESSOAL")
	private String cepEnderecoPessoal;

	@Column(name="DATA_ANIVERSARIO")
//	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDate dataAniversario;

	@Column(name="DATA_CRIACAO", insertable = false, updatable = false)
	@JsonIgnore
	private LocalDate dataCriacao;
	
	@NotEmpty(message = "nome é obrigatório")
	@NotNull
	@Column(name="NOME")
	private String nome;

	@NotNull
	@DecimalMin(value = "10000000000", message = "Telefone Contato deve ter no mínimo '10' dígitos")
	@DecimalMax(value = "99999999999", message = "Telefone Contato deve ter no máximo '11' dígitos")
	@Column(name="TELEFONE_CONTATO")
	private Long telefoneContato;
	
	@NotNull
	@DecimalMin(value = "10000000000", message = "CPF deve ter no mínimo '11' dígitos")
	@DecimalMax(value = "99999999999", message = "CPF deve ter no máximo '11' dígitos")
	@Column(name="CPF")
	private Long cpf;

	/*
		Spring security Fields
	 */
	@NotNull
	@NotEmpty(message = "Password é obrigatória")
	@Column(name="SENHA")
	private String password;

	@Transient
	private String newPassword;

	@NotEmpty(message = "email é obrigatório")
	@NotNull
	@EmailValidator
	@Column(name="EMAIL", updatable = false)
	private String username;

	@Column(name="HABILITADO")
	private boolean enabled;

	@Column(name="EMAIL_CONFIRMADO")
	private boolean emailConfirmado;

	@Column(name="TOKEN_VALIDACAO_EMAIL")
	@JsonIgnore
	private String tokenValidacaoEmail;

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
		this.tokenValidacaoEmail = usuario.getTokenValidacaoEmail();
		this.enabled = false;
		this.emailConfirmado = false;
	}

	@PrePersist
	private void prePersist() {
		this.setEnabled(true);
		this.setEmailConfirmado(false);
		this.setDataCriacao(LocalDate.now());
		this.setId(null);
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
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return enabled;
	}
}
