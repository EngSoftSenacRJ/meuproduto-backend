package senac.edu.engsoft.meuproduto.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.repository.UsuarioFuncionarioRepository;

import java.util.Optional;

@Service
public class UsuarioFuncionarioServiceImpl implements UsuarioFuncionarioService {

	private final UsuarioFuncionarioRepository usuarioFuncionarioRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioFuncionarioServiceImpl(UsuarioFuncionarioRepository usuarioFuncionarioRepository,
										 BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.usuarioFuncionarioRepository = usuarioFuncionarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
		String encodedPassword = bCryptPasswordEncoder.encode(usuarioFuncionario.getPassword());
		usuarioFuncionario.setPassword(encodedPassword);
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

	@Override
	public Iterable<UsuarioFuncionario> getAllEnabled() {
		try {
			return usuarioFuncionarioRepository.findAllEnabled();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
