package senac.edu.engsoft.meuproduto.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.repository.UsuarioAdministradorRepository;

@Service
public class UsuarioAdministradorServiceImpl implements UsuarioAdministradorService {

	private final UsuarioAdministradorRepository usuarioAdministradorRepository;
	
	public UsuarioAdministradorServiceImpl(UsuarioAdministradorRepository usuarioAdministradorRepository) {
		super();
		this.usuarioAdministradorRepository = usuarioAdministradorRepository;
	}

	@Override
	public Optional<UsuarioAdministrador> getById(Long id) {
		try {
			return usuarioAdministradorRepository.findById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Optional<UsuarioAdministrador> getByNome(String nome) {
		try {
			return usuarioAdministradorRepository.getByNome(nome);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UsuarioAdministrador saveOrUpdate(UsuarioAdministrador usuarioAdministrador) {
		try {
			return usuarioAdministradorRepository.save(usuarioAdministrador);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {
			usuarioAdministradorRepository.deleteById(id);
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try {
			usuarioAdministradorRepository.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Iterable<UsuarioAdministrador> getAll() {
		try {
			return usuarioAdministradorRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
