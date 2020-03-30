package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;
import senac.edu.engsoft.meuproduto.service.repository.CategoriaProdutoRepository;

import java.util.Optional;

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

	@Override
	public Optional<CategoriaProduto> getById(Long id) {
		return categoriaProdutoRepository.findById(id);
	}

}
