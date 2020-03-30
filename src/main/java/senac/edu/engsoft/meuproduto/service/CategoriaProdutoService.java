package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.CategoriaProduto;

import java.util.Optional;

public interface CategoriaProdutoService {

	Iterable<CategoriaProduto> getAll();
    Optional<CategoriaProduto> getById(Long id);
}
