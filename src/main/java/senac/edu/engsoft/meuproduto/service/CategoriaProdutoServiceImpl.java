package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService {

	private final CategoriaProdutoRepository categoriaProdutoRepository;

	public CategoriaProdutoServiceImpl(CategoriaProdutoRepository categoriaProdutoRepository) {
		super();
		this.categoriaProdutoRepository = categoriaProdutoRepository;
	}

	@Override
	public Iterable<CategoriaProduto> getAll() {
		return categoriaProdutoRepository.findAll();
	}

}
