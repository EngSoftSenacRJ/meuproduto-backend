package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoAuditDTO;

import java.util.List;
import java.util.Optional;

public interface LojaProdutoAuditService {

	List<LojaProdutoAuditDTO> getAudit(Optional<Long> idLoja, Optional<Long> idProduto);
}
