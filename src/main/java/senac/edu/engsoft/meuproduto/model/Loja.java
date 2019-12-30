package senac.edu.engsoft.meuproduto.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LOJA")
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="razao_social")
	private String razaoSocial;
	
	@Column(name="cnpj")
	private String cnpj;
	
	@Column(name="rua_endereco_comercial")
	private String ruaEnderecoComercial;
	
	@Column(name="numero_endereco_comercial")
	private String numeroEnderecoComercial;
	
	@Column(name="bairro_endereco_comercial")
	private String bairroEnderecoComercial;
	
	@Column(name="cidade_endereco_comercial")
	private String cidadeEnderecoComercial;
	
	@Column(name="estado_endereco_comercial")
	private String estadoEnderecoComercial;
	
	@Column(name="cep_endereco_comercial")
	private String cepEnderecoComercial;
	
	@Column(name="TELEFONE_CONTATO")
	private String telefoneContato;
	
	/*
	 * Criar os 7 dias da semana automaticamente ao criar uma loja
	 */
	@OneToMany(mappedBy = "loja", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<HorarioFuncionamento> horarioFuncionamentoSet = new HashSet<>();
	
	@ManyToMany(mappedBy="lojas")
	private Set<Usuario> usuarios = new HashSet<>();
	
	@OneToMany(mappedBy = "loja", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<LojaProduto> lojaProdutoSet = new HashSet<>();
	
	public Loja() {
		super();
	}

	public Loja(Date dataCriacao, String nome, String razaoSocial, String cnpj, String ruaEnderecoComercial,
			String numeroEnderecoComercial, String bairroEnderecoComercial, String cidadeEnderecoComercial,
			String estadoEnderecoComercial, String cepEnderecoComercial, String telefoneContato,
			Set<HorarioFuncionamento> horarioFuncionamentoSet) {
		super();
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.ruaEnderecoComercial = ruaEnderecoComercial;
		this.numeroEnderecoComercial = numeroEnderecoComercial;
		this.bairroEnderecoComercial = bairroEnderecoComercial;
		this.cidadeEnderecoComercial = cidadeEnderecoComercial;
		this.estadoEnderecoComercial = estadoEnderecoComercial;
		this.cepEnderecoComercial = cepEnderecoComercial;
		this.telefoneContato = telefoneContato;
		this.horarioFuncionamentoSet = horarioFuncionamentoSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRuaEnderecoComercial() {
		return ruaEnderecoComercial;
	}

	public void setRuaEnderecoComercial(String ruaEnderecoComercial) {
		this.ruaEnderecoComercial = ruaEnderecoComercial;
	}

	public String getNumeroEnderecoComercial() {
		return numeroEnderecoComercial;
	}

	public void setNumeroEnderecoComercial(String numeroEnderecoComercial) {
		this.numeroEnderecoComercial = numeroEnderecoComercial;
	}

	public String getBairroEnderecoComercial() {
		return bairroEnderecoComercial;
	}

	public void setBairroEnderecoComercial(String bairroEnderecoComercial) {
		this.bairroEnderecoComercial = bairroEnderecoComercial;
	}

	public String getCidadeEnderecoComercial() {
		return cidadeEnderecoComercial;
	}

	public void setCidadeEnderecoComercial(String cidadeEnderecoComercial) {
		this.cidadeEnderecoComercial = cidadeEnderecoComercial;
	}

	public String getEstadoEnderecoComercial() {
		return estadoEnderecoComercial;
	}

	public void setEstadoEnderecoComercial(String estadoEnderecoComercial) {
		this.estadoEnderecoComercial = estadoEnderecoComercial;
	}

	public String getCepEnderecoComercial() {
		return cepEnderecoComercial;
	}

	public void setCepEnderecoComercial(String cepEnderecoComercial) {
		this.cepEnderecoComercial = cepEnderecoComercial;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public Set<HorarioFuncionamento> getHorarioFuncionamentoSet() {
		return horarioFuncionamentoSet;
	}

	public void setHorarioFuncionamentoSet(Set<HorarioFuncionamento> horarioFuncionamentoSet) {
		this.horarioFuncionamentoSet = horarioFuncionamentoSet;
	}

	public Set<LojaProduto> getLojaProdutoSet() {
		return lojaProdutoSet;
	}

	public void setLojaProdutoSet(Set<LojaProduto> lojaProdutoSet) {
		this.lojaProdutoSet = lojaProdutoSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
