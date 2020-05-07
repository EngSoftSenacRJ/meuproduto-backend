package senac.edu.engsoft.meuproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.edu.engsoft.meuproduto.model.resource.LojaProdutoAuditDTO;
import senac.edu.engsoft.meuproduto.service.LojaProdutoAuditService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping({"/lojasProdutos/audit"})
@Tag(name = "Auditoria Loja/Produto", description = "Auditoria - Loja/Produto API")
public class LojaProdutoAuditController {

	private LojaProdutoAuditService lojaProdutoAuditService;

	@Autowired
	public LojaProdutoAuditController(LojaProdutoAuditService lojaProdutoAuditService) {
		this.lojaProdutoAuditService = lojaProdutoAuditService;
	}

	@GetMapping
	@Operation(summary = "Buscar Loja/Produto", description = "Buscar lista de Loja/Produto")
	public ResponseEntity<List<LojaProdutoAuditDTO>> consult(
			@RequestParam("idLoja") Optional<Long> idLoja,
			@RequestParam("idProduto") Optional<Long> idProduto
	) {
		return new ResponseEntity<>(lojaProdutoAuditService.getAudit(idLoja.get(), idProduto.get()), HttpStatus.OK);
	}
	
}
