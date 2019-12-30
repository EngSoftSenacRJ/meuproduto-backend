package senac.edu.engsoft.meuproduto.model.resource;

import org.springframework.hateoas.RepresentationModel;

public class LojaResource extends RepresentationModel<LojaResource> {

//	@ApiModelProperty(notes="Nome da Loja", required=false)
	private String nome;
	
//	@ApiModelProperty(notes="Endereço completo da Loja", required=false)
	private String enderecoComercialCompleto;

	public LojaResource() {
		super();
	}

	public LojaResource(String nome, String enderecoComercialCompleto) {
		super();
		this.nome = nome;
		this.enderecoComercialCompleto = enderecoComercialCompleto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnderecoComercialCompleto() {
		return enderecoComercialCompleto;
	}

	public void setEnderecoComercialCompleto(String enderecoComercialCompleto) {
		this.enderecoComercialCompleto = enderecoComercialCompleto;
	}
	
}
