package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import senac.edu.engsoft.meuproduto.model.AuditAction;

import java.util.Date;

@Getter
@Setter
@JsonInclude
public class LojaProdutoAuditDTO {

	private String nomeLoja;
	private String nomeProduto;
	private Double preco;
	private AuditAction acao;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAcao;

	public LojaProdutoAuditDTO() {
		super();
	}

	public LojaProdutoAuditDTO(String nomeLoja, String nomeProduto, Double preco, AuditAction acao, Date dataAcao) {
		this.nomeLoja = nomeLoja;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.acao = acao;
		this.dataAcao = dataAcao;
	}

}
