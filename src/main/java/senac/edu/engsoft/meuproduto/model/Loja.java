package senac.edu.engsoft.meuproduto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
//@Spatial
@Entity
@Table(name = "TB_LOJA")
@JsonInclude
@Indexed
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(name="data_criacao")
	@JsonIgnore
	private LocalDateTime dataCriacao;

//	@Latitude
	@Column(name="LATITUDE")
//	@Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$", message = "Formato da latitude é inválida")
	private Double latitude;

//	@Longitude
	@Column(name="LONGITUDE")
//	@Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$", message = "Formato da longitude é inválida")
	private Double longitude;

	@NotNull(message = "Nome da loja é obrigatório")
	@NotEmpty(message = "Nome da loja é obrigatório")
	@Pattern(regexp = "[a-zA-Z ]{3,50}", message = "Nome da loja deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="nome")
	@Field
	@Analyzer(definition = "customanalyzer")
	private String nome;

	@NotNull(message = "Razão Social da loja é obrigatório")
	@NotEmpty(message = "Razão Social da loja é obrigatório")
	@Size(min = 5, max = 100, message = "Razão Social da loja deve ter no mínimo '5' e no máximo '100' caracteres")
	@Column(name="razao_social")
	private String razaoSocial;

	@NotNull(message = "CNPJ da loja é obrigatório")
	@NotEmpty(message = "CNPJ da loja é obrigatório")
	@Pattern(regexp = "[0-9]{14}", message = "CNPJ da loja deve ter '14' caracteres")
	@Column(name="cnpj")
	private String cnpj;

	@NotNull(message = "Rua Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Rua Endereço Comercial da loja é obrigatório")
	@Size(min = 3, max = 50, message = "Rua Endereço Comercial da loja deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="rua_endereco_comercial")
	private String ruaEnderecoComercial;

	@NotNull(message = "Número Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Número Endereço Comercial da loja é obrigatório")
	@Pattern(regexp = "[0-9]{1,10}", message = "Número Endereço Comercial deve ter no mínimo '1' e no máximo '10' dígitos")
	@Column(name="numero_endereco_comercial")
	private String numeroEnderecoComercial;

	@NotNull(message = "Bairro Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Bairro Endereço Comercial da loja é obrigatório")
	@Size(min = 3, max = 50, message = "Bairro Endereço Comercial da loja deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="bairro_endereco_comercial")
	private String bairroEnderecoComercial;

	@NotNull(message = "Cidade Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Cidade Endereço Comercial da loja é obrigatório")
	@Size(min = 3, max = 50, message = "Cidade Endereço Comercial da loja deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="cidade_endereco_comercial")
	private String cidadeEnderecoComercial;

	@NotNull(message = "Estado Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Estado Endereço Comercial da loja é obrigatório")
	@Size(min = 3, max = 50, message = "Estado Endereço Comercial da loja deve ter no mínimo '3' e no máximo '50' caracteres")
	@Column(name="estado_endereco_comercial")
	private String estadoEnderecoComercial;

	@NotNull(message = "CEP Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "CEP Endereço Comercial da loja é obrigatório")
	@Pattern(regexp = "[0-9]{8}", message = "CEP Endereço Comercial da loja deve ter '8' dígitos")
	@Column(name="cep_endereco_comercial")
	private String cepEnderecoComercial;

	@NotNull(message = "Telefone Contato Endereço Comercial da loja é obrigatório")
	@NotEmpty(message = "Telefone Contato Endereço Comercial da loja é obrigatório")
	@Pattern(regexp = "[0-9]{10,11}", message = "Telefone Contato da loja deve ter no mínimo '10' e no máximo '11' dígitos")
	@Column(name="TELEFONE_CONTATO")
	private String telefoneContato;

	@NotNull(message = "Email do Usuário é obrigatório para criar loja")
	@NotEmpty(message = "Email do Usuário é obrigatório para criar loja")
	@Pattern(regexp = "^(.+)@(.+)$", message = "Email Usuario Criador Loja é inválido")
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
