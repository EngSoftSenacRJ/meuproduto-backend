package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_LOJA")
@JsonInclude
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(name="data_criacao")
	@JsonIgnore
	private LocalDateTime dataCriacao;

	@Column(name="LATITUDE")
	private String latitude;

	@Column(name="LONGITUDE")
	private String longitude;

	@NotNull(message = "Nome da loja é obrigatório")
	@NotEmpty(message = "Nome da loja é obrigatório")
	@Size(min = 3, max = 50, message = "Nome da loja deve ter no mpinimo '3' e no máximo '50' caracteres")
	@Column(name="nome")
	private String nome;

	@NotNull(message = "Razão Social da loja é obrigatório")
	@NotEmpty(message = "Razão Social da loja é obrigatório")
	@Column(name="razao_social")
	private String razaoSocial;

	@NotNull(message = "CNPJ da loja é obrigatório")
	@NotEmpty(message = "CNPJ da loja é obrigatório")
	@Column(name="cnpj")
	private String cnpj;

	@NotNull(message = "Rua Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Rua Endereço Comercial da loja é obrigatório")
	@Column(name="rua_endereco_comercial")
	private String ruaEnderecoComercial;

	@NotNull(message = "Número Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Número Endereço Comercial da loja é obrigatório")
	@Column(name="numero_endereco_comercial")
	private String numeroEnderecoComercial;

	@NotNull(message = "Bairro Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Bairro Endereço Comercial da loja é obrigatório")
	@Column(name="bairro_endereco_comercial")
	private String bairroEnderecoComercial;

	@NotNull(message = "Cidade Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Cidade Endereço Comercial da loja é obrigatório")
	@Column(name="cidade_endereco_comercial")
	private String cidadeEnderecoComercial;

	@NotNull(message = "Estado Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Estado Endereço Comercial da loja é obrigatório")
	@Column(name="estado_endereco_comercial")
	private String estadoEnderecoComercial;

	@NotNull(message = "CEP Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "CEP Endereço Comercial da loja é obrigatório")
	@Column(name="cep_endereco_comercial")
	private String cepEnderecoComercial;

	@NotNull(message = "Telefone Contato Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Telefone Contato Endereço Comercial da loja é obrigatório")
	@Column(name="TELEFONE_CONTATO")
	private String telefoneContato;

	@NotNull(message = "Email do Usuário é obrigatório para criar loja")
	@NotEmpty(message = "Email do Usuário é obrigatório para criar loja")
	@Column(name="EMAIL_USUARIO_CRIADOR_LOJA")
	private String emailUsuarioCriadorLoja;
	
	/*
	 * Criar os 7 dias da semana automaticamente ao criar uma loja
	 */
	@OneToMany(mappedBy = "loja", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JsonIgnore
	private Set<HorarioFuncionamento> horarioFuncionamentoSet = new HashSet<>();
	
	@ManyToMany(mappedBy="lojas")
	@JsonIgnore
	private Set<Usuario> usuarios = new HashSet<>();
	
	@OneToMany(mappedBy = "loja", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JsonIgnore
	private Set<LojaProduto> lojaProdutoSet = new HashSet<>();

	@PostPersist
	public void postPersist(){
		setDataCriacao(LocalDateTime.now());
	}
	
	public Loja() {
		super();
	}

//	public Loja(LocalDateTime dataCriacao, String nome, String razaoSocial, String cnpj, String ruaEnderecoComercial,
//			String numeroEnderecoComercial, String bairroEnderecoComercial, String cidadeEnderecoComercial,
//			String estadoEnderecoComercial, String cepEnderecoComercial, String telefoneContato,
//			Set<HorarioFuncionamento> horarioFuncionamentoSet) {
//		super();
//		this.dataCriacao = dataCriacao;
//		this.nome = nome;
//		this.razaoSocial = razaoSocial;
//		this.cnpj = cnpj;
//		this.ruaEnderecoComercial = ruaEnderecoComercial;
//		this.numeroEnderecoComercial = numeroEnderecoComercial;
//		this.bairroEnderecoComercial = bairroEnderecoComercial;
//		this.cidadeEnderecoComercial = cidadeEnderecoComercial;
//		this.estadoEnderecoComercial = estadoEnderecoComercial;
//		this.cepEnderecoComercial = cepEnderecoComercial;
//		this.telefoneContato = telefoneContato;
//		this.horarioFuncionamentoSet = horarioFuncionamentoSet;
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Loja)) return false;
		Loja loja = (Loja) o;
		return Objects.equals(id, loja.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void copyForNew(Loja other) {
		if(other.getNome() != null)
			this.setNome(other.getNome());
		if(other.getRazaoSocial() != null)
			this.setRazaoSocial(other.getRazaoSocial());
		if(other.getCnpj() != null)
			this.setCnpj(other.getCnpj());
		if(other.getRuaEnderecoComercial() != null)
			this.setRuaEnderecoComercial(other.getRuaEnderecoComercial());
		if(other.getNumeroEnderecoComercial() != null)
			this.setNumeroEnderecoComercial(other.getNumeroEnderecoComercial());
		if(other.getBairroEnderecoComercial() != null)
			this.setBairroEnderecoComercial(other.getBairroEnderecoComercial());
		if(other.getCidadeEnderecoComercial() != null)
			this.setCidadeEnderecoComercial(other.getCidadeEnderecoComercial());
		if(other.getEstadoEnderecoComercial() != null)
			this.setEstadoEnderecoComercial(other.getEstadoEnderecoComercial());
		if(other.getCepEnderecoComercial() != null)
			this.setCepEnderecoComercial(other.getCepEnderecoComercial());
		if(other.getTelefoneContato() != null)
			this.setTelefoneContato(other.getTelefoneContato());
		if(other.getEmailUsuarioCriadorLoja() != null)
			this.setEmailUsuarioCriadorLoja(other.getEmailUsuarioCriadorLoja());
		if(other.getLatitude() != null)
			this.setLatitude(other.getLatitude());
		if(other.getLongitude() != null)
			this.setLatitude(other.getLongitude());
	}
}
