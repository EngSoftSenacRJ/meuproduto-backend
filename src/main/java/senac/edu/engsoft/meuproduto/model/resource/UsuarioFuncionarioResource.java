package senac.edu.engsoft.meuproduto.model.resource;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class UsuarioFuncionarioResource extends RepresentationModel<UsuarioFuncionarioResource> {

	private Date dataCriacao;
	private String nome;
	private String telefoneContato;
	private String cpf;
	private String nomePerfil;
	

	public UsuarioFuncionarioResource() {
		super();
	}

	public UsuarioFuncionarioResource(Date dataCriacao, String nome, String telefoneContato, String cpf,
			String nomePerfil) {
		super();
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.cpf = cpf;
		this.nomePerfil = nomePerfil;
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

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	
}
