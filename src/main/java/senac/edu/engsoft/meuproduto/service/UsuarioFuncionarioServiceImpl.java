package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.repository.UsuarioFuncionarioRepository;

@Service
public class UsuarioFuncionarioServiceImpl implements UsuarioFuncionarioService {

	private final UsuarioFuncionarioRepository usuarioFuncionarioRepository;
	
	public UsuarioFuncionarioServiceImpl(UsuarioFuncionarioRepository usuarioFuncionarioRepository) {
		super();
		this.usuarioFuncionarioRepository = usuarioFuncionarioRepository;
	}

	@Override
	public Optional<UsuarioFuncionario> getById(Long id) {
		try {
			return usuarioFuncionarioRepository.findById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Optional<UsuarioFuncionario> getByNome(String nome) {
		try {
			return usuarioFuncionarioRepository.getByNome(nome);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UsuarioFuncionario saveOrUpdate(UsuarioFuncionario usuarioFuncionario) {
		try {
			return usuarioFuncionarioRepository.save(usuarioFuncionario);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {
			usuarioFuncionarioRepository.deleteById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try {
			usuarioFuncionarioRepository.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Iterable<UsuarioFuncionario> getAll() {
		try {
			return usuarioFuncionarioRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
