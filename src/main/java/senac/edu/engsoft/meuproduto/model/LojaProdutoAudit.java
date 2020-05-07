//package senac.edu.engsoft.meuproduto.model;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//
//@Entity
//@Table(name = "TB_LOJA_PRODUTO_AUD")
//@Getter
//@Setter
//@EqualsAndHashCode
//public class LojaProdutoAudit  {
//
//	@EmbeddedId
//	private LojaProdutoAuditPK lojaProdutoAuditPK;
//
//	@Column(name = "REVTYPE")
//	private Integer revType;
//
//	@Column(name = "PRECO")
//	private Double preco;
//
//	@Column(name = "ID_LOJA")
//	private Long idLoja;
//
//	@Column(name = "ID_PRODUTO")
//	private Long idProduto;
//
//	public LojaProdutoAudit() {
//		super();
//	}
//
//}
