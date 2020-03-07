package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.ObjectCreationValidationException;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;
import senac.edu.engsoft.meuproduto.repository.MarcaProdutoRepository;

import java.util.Optional;

@Service
public class MarcaProdutoServiceImpl implements MarcaProdutoService {

	private final MarcaProdutoRepository marcaProdutoRepository;

	public MarcaProdutoServiceImpl(MarcaProdutoRepository categoriaProdutoRepository) {
		super();
		this.marcaProdutoRepository = categoriaProdutoRepository;
	}

	@Override
	public Iterable<MarcaProduto> getAll() {
		return marcaProdutoRepository.findAll();
	}

	@Override
	public Optional<MarcaProduto> getById(Long id) {
		return marcaProdutoRepository.findById(id);
	}

	@Override
	public MarcaProduto update(MarcaProduto marcaProduto) {
		return marcaProdutoRepository.save(marcaProduto);
	}

	@Override
	public void delete(Long id) {
		marcaProdutoRepository.deleteById(id);
	}

	@Override
	public MarcaProduto save(MarcaProduto marcaProduto) {
		Optional<MarcaProduto> existingMarcaProduto = marcaProdutoRepository.getByNome(marcaProduto.getNome());
		if(existingMarcaProduto.isPresent()){
			throw new ObjectCreationValidationException("Marca já existente com esse nome: " + marcaProduto.getNome());
		}

		return marcaProdutoRepository.save(marcaProduto);
	}

}
