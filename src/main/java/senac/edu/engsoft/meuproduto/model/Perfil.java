//package senac.edu.engsoft.meuproduto.model;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="TB_PERFIL")
//public class Perfil {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(name="NOME")
//	private String nome;
//	
//	@Column(name="DESCRICAO")
//	private String descricao;
//	
//	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
//	private Set<Usuario> usuarios = new HashSet<>();
//
//	public Perfil() {
//		super();
//	}
//
//	public Perfil(String nome, String descricao) {
//		super();
//		this.nome = nome;
//		this.descricao = descricao;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public Set<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(Set<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Perfil other = (Perfil) obj;
//		if (descricao == null) {
//			if (other.descricao != null)
//				return false;
//		} else if (!descricao.equals(other.descricao))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (nome == null) {
//			if (other.nome != null)
//				return false;
//		} else if (!nome.equals(other.nome))
//			return false;
//		return true;
//	}
//
//}
