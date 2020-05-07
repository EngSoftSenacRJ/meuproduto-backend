package senac.edu.engsoft.meuproduto.service;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.envers.RevisionInfo;
import senac.edu.engsoft.meuproduto.model.AuditAction;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoAuditDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class LojaProdutoAuditServiceImpl implements LojaProdutoAuditService {

	@PersistenceContext
	private EntityManager em;

//	private final LojaProdutoAuditRepository lojaProdutoAuditRepository;

	public LojaProdutoAuditServiceImpl(
//			LojaProdutoAuditRepository lojaProdutoAuditRepository
	) {
		super();
//		this.lojaProdutoAuditRepository = lojaProdutoAuditRepository;
	}

	@Override
	public List<LojaProdutoAuditDTO> getAudit(Long idLoja, Long idProduto) {
		AuditReader reader = AuditReaderFactory.get(em);
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(LojaProduto.class, false, true);
		query.add(AuditEntity.relatedId("loja").eq(idLoja));
		query.add(AuditEntity.relatedId("produto").eq(idProduto));
		List<Object[]> results = query.getResultList();
		List<LojaProdutoAuditDTO> lojaProdutoAuditResourceList = new ArrayList<>();
		for (Object row : results) {
			if (row instanceof Object[]) {
				Object[] array = (Object[]) row;
				LojaProduto lojaProduto = (LojaProduto) array[0];
				RevisionInfo revisionInfo = ((RevisionInfo) array[1]);
				RevisionType revisionType = ((RevisionType) array[2]);

				AuditAction auditAction;
				switch (revisionType.getRepresentation()) {
					case 0:
						auditAction = AuditAction.INSERT;
						break;
					case 1:
						auditAction = AuditAction.UPDATE;
						break;
					case 2:
						auditAction = AuditAction.DELETE;
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + revisionType);
				}

				lojaProdutoAuditResourceList.add(
						new LojaProdutoAuditDTO(
								lojaProduto.getLoja().getNome(),
								lojaProduto.getProduto().getNome(),
								lojaProduto.getPreco(),
								auditAction,
								revisionInfo.getTimestamp()
						)
				);
			}
		}
		return lojaProdutoAuditResourceList;
	}

}
