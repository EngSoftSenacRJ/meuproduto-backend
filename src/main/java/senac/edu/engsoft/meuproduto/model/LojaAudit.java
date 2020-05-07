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
//@Table(name = "TB_LOJA_AUD")
//@Getter
//@Setter
//public class LojaAudit {
//
//	@EmbeddedId
//	private LojaAuditPK lojaAuditPK;
//
//	@Column(name = "NOME")
//	private String nome;
//
//	@OneToMany(mappedBy = "lojaAudit", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
//	Set<LojaProdutoAudit> lojaProdutoAuditSet;
//
//	public LojaAudit() {
//		super();
//	}
//
//}
