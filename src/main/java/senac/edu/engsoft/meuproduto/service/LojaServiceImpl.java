package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.repository.LojaRepository;

@Service
public class LojaServiceImpl implements LojaService {

	private final LojaRepository lojaRepository;
	
	public LojaServiceImpl(LojaRepository lojaRepository) {
		super();
		this.lojaRepository = lojaRepository;
	}

	@Override
	public Optional<Loja> getById(Long id) {
		try {
			return lojaRepository.findById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Optional<Loja> getByNome(String nome) {
		try {
			return lojaRepository.getByNome(nome);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Loja saveOrUpdate(Loja loja) {
		try {
			return lojaRepository.save(loja);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {
			lojaRepository.deleteById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try {
			lojaRepository.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Iterable<Loja> getAll() {
		try {
			return lojaRepository.findAll();
//			Iterable<Loja> iLojas = lojaRepository.findAll();
//			List<Loja> lojas = new ArrayList<>();
//			iLojas.forEach(lojas::add);
//			return lojas;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
