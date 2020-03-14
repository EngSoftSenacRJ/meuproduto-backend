package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.repository.UsuarioFuncionarioRepository;

import java.util.Optional;

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
	public UsuarioFuncionario save(UsuarioFuncionario usuarioFuncionario) {
		return usuarioFuncionarioRepository.save(usuarioFuncionario); //save
	}

	@Override
	public UsuarioFuncionario update(Long id, UsuarioFuncionario usuarioFuncionario) {
		Optional<UsuarioFuncionario> usuarioFuncionarioEncontrado = getById(id);
		UsuarioFuncionario usuarioFuncionarioParaAtualizar = usuarioFuncionarioEncontrado.get();
		usuarioFuncionarioEncontrado.get().copyForNew(usuarioFuncionario);
		return usuarioFuncionarioRepository.save(usuarioFuncionarioParaAtualizar); //update
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
