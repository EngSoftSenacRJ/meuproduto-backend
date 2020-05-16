package senac.edu.engsoft.meuproduto.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.LojaNotFoundException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;
import senac.edu.engsoft.meuproduto.service.repository.LojaRepository;
import senac.edu.engsoft.meuproduto.service.repository.UsuarioFuncionarioRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioFuncionarioServiceImpl implements UsuarioFuncionarioService {

	private final UsuarioFuncionarioRepository usuarioFuncionarioRepository;
	private final LojaRepository lojaRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioFuncionarioServiceImpl(UsuarioFuncionarioRepository usuarioFuncionarioRepository,
										 LojaRepository lojaRepository,
										 BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.usuarioFuncionarioRepository = usuarioFuncionarioRepository;
		this.lojaRepository = lojaRepository;
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
		String token = UUID.randomUUID().toString();
		usuarioFuncionario.setTokenValidacaoEmail(token);

		if(usuarioFuncionario.getLojaId() != null) {
			Optional<Loja> loja = lojaRepository.findById(usuarioFuncionario.getLojaId());
			if(!loja.isPresent())
				throw new LojaNotFoundException();
			usuarioFuncionario.setLoja(loja.get());
		}

		return usuarioFuncionarioRepository.save(usuarioFuncionario); //save
	}

	@Override
	public UsuarioFuncionario update(Long id, UsuarioFuncionario usuarioFuncionario) {
		Optional<UsuarioFuncionario> usuarioFuncionarioEncontrado = getById(id);
		UsuarioFuncionario usuarioFuncionarioParaAtualizar = usuarioFuncionarioEncontrado.get();
		usuarioFuncionarioEncontrado.get().copyForNew(usuarioFuncionario);
		String encodedPassword = bCryptPasswordEncoder.encode(usuarioFuncionario.getNewPassword());
		usuarioFuncionarioParaAtualizar.setPassword(encodedPassword);
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

	@Override
	public Iterable<UsuarioFuncionario> getByUsernameAdministrador(String usernameAdministrador, Boolean enabled) {
		try {
			return usuarioFuncionarioRepository.getByUsernameAdministrador(usernameAdministrador, enabled);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
