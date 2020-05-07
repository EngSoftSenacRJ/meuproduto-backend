//package senac.edu.engsoft.meuproduto.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Set;
//
//
//@Entity
//@Table(name = "TB_PRODUTO_AUD")
//@Getter
//@Setter
//public class ProdutoAudit {
//
//	@EmbeddedId
//	private ProdutoAuditPK produtoAuditPK;
//
//	@Column(name = "NOME")
//	private String nome;
//
//	@OneToMany(mappedBy = "produtoAudit", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
//	Set<LojaProdutoAudit> lojaProdutoAuditSet;
//
//	public ProdutoAudit() {
//		super();
//	}
//
//}
