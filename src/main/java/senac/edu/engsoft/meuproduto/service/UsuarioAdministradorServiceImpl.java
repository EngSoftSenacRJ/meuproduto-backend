package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.AdministratorUserFailedDeleteException;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.repository.UsuarioAdministradorRepository;

import java.util.Optional;

@Service
public class UsuarioAdministradorServiceImpl implements UsuarioAdministradorService {

	private final UsuarioAdministradorRepository usuarioAdministradorRepository;
	
	public UsuarioAdministradorServiceImpl(UsuarioAdministradorRepository usuarioAdministradorRepository) {
		super();
		this.usuarioAdministradorRepository = usuarioAdministradorRepository;
	}

	@Override
	public Optional<UsuarioAdministrador> getById(Long id) {
		return usuarioAdministradorRepository.findById(id);
	}
	
	@Override
	public Optional<UsuarioAdministrador> getByNome(String nome) {
		return usuarioAdministradorRepository.getByNome(nome);
	}
	
	@Override
	public Optional<UsuarioAdministrador> getByCpf(Long cpf) {
		return usuarioAdministradorRepository.getByCpf(cpf);
	}
	
	@Override
	public Optional<UsuarioAdministrador> getByEmail(String email) {
		return usuarioAdministradorRepository.getByEmail(email);
	}

//	@Override
//	public UsuarioAdministrador save(UsuarioAdministrador usuarioAdministrador)
//			throws UserEmptyCpfException,
//			UserCpfAlreadyExistsException,
//			UserInvalidCpfException {
//		Long usuarioAdministradorCpf = usuarioAdministrador.getCpf();
//
//		if(usuarioAdministradorCpf == null) {
//			throw new UserEmptyCpfException();
//		}
//		else if(!CpfValidatorUtil.isValidCpf(Long.toString(usuarioAdministradorCpf))) {
//			throw new UserInvalidCpfException(usuarioAdministradorCpf);
//		}
//		else if (this.getByCpf(usuarioAdministradorCpf).isPresent()){
//			throw new UserCpfAlreadyExistsException(usuarioAdministradorCpf);
//		}
//		else {
//			return usuarioAdministradorRepository.save(usuarioAdministrador);
//		}
//	}
	
	@Override
	public UsuarioAdministrador update(UsuarioAdministrador usuarioAdministrador) throws AdministratorUserFailedDeleteException {
		return usuarioAdministradorRepository.save(usuarioAdministrador);
	}

	@Override
	public void delete(Long id) {
		usuarioAdministradorRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		usuarioAdministradorRepository.deleteAll();
	}

	@Override
	public Iterable<UsuarioAdministrador> getAll() {
		return usuarioAdministradorRepository.findAll();
	}
	
}
