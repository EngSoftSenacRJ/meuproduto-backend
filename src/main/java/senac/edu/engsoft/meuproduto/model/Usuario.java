package senac.edu.engsoft.meuproduto.model;

import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE")
	private UsuarioType usuarioType;
	
	@Column(name="rua_endereco_pessoal")
	private String ruaEnderecoPessoal;
	
	@Column(name="numero_endereco_pessoal")
	private String numeroEnderecoPessoal;
	
	@Column(name="bairro_endereco_pessoal")
	private String bairroEnderecoPessoal;
	
	@Column(name="cidade_endereco_pessoal")
	private String cidadeEnderecoPessoal;
	
	@Column(name="estado_endereco_pessoal")
	private String estadoEnderecoPessoal;
	
	@Column(name="cep_endereco_pessoal")
	private String cepEnderecoPessoal;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="SALT")
	private String salt;
	
	@Column(name="DATA_CRIACAO")
	private Date dataCriacao;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="TELEFONE_CONTATO")
	private String telefoneContato;
	
	@Column(name="CPF")
	private String cpf;
	
	@JoinColumn(name = "ID_PERFIL")
	@ManyToOne(fetch = FetchType.LAZY)
	private Perfil perfil;
	
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_LOJA",
		joinColumns = { @JoinColumn(name = "ID_USUARIO") },
		inverseJoinColumns = { @JoinColumn(name = "ID_LOJA") })
	private Set<Loja> lojas = new HashSet<>();
	
	public Usuario() {
		super();
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

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(Set<Loja> lojas) {
		this.lojas = lojas;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public UsuarioType getUsuarioType() {
		return usuarioType;
	}

	public void setUsuarioType(UsuarioType usuarioType) {
		this.usuarioType = usuarioType;
	}

	public String getRuaEnderecoPessoal() {
		return ruaEnderecoPessoal;
	}

	public void setRuaEnderecoPessoal(String ruaEnderecoPessoal) {
		this.ruaEnderecoPessoal = ruaEnderecoPessoal;
	}

	public String getNumeroEnderecoPessoal() {
		return numeroEnderecoPessoal;
	}

	public void setNumeroEnderecoPessoal(String numeroEnderecoPessoal) {
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
	}

	public String getBairroEnderecoPessoal() {
		return bairroEnderecoPessoal;
	}

	public void setBairroEnderecoPessoal(String bairroEnderecoPessoal) {
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
	}

	public String getCidadeEnderecoPessoal() {
		return cidadeEnderecoPessoal;
	}

	public void setCidadeEnderecoPessoal(String cidadeEnderecoPessoal) {
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
	}

	public String getEstadoEnderecoPessoal() {
		return estadoEnderecoPessoal;
	}

	public void setEstadoEnderecoPessoal(String estadoEnderecoPessoal) {
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
	}

	public String getCepEnderecoPessoal() {
		return cepEnderecoPessoal;
	}

	public void setCepEnderecoPessoal(String cepEnderecoPessoal) {
		this.cepEnderecoPessoal = cepEnderecoPessoal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefoneContato == null) ? 0 : telefoneContato.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefoneContato == null) {
			if (other.telefoneContato != null)
				return false;
		} else if (!telefoneContato.equals(other.telefoneContato))
			return false;
		return true;
	}
	
}
