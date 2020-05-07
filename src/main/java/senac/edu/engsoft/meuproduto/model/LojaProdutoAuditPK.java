//package senac.edu.engsoft.meuproduto.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import senac.edu.engsoft.meuproduto.envers.RevisionInfo;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Embeddable
//@Getter
//@Setter
//public class LojaProdutoAuditPK implements Serializable {
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "REV")
//	private RevisionInfo revisionInfo;
//
//	@Column(name = "ID")
//	private Long id;
//
//	public LojaProdutoAuditPK() {
//		super();
//	}
//
//}
