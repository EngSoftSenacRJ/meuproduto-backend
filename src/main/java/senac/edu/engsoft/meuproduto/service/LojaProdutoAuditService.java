package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoAuditDTO;

import java.util.List;

public interface LojaProdutoAuditService {

	List<LojaProdutoAuditDTO> getAudit(Long idLoja, Long idProduto);
}
