package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.ObjectCreationValidationException;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.repository.UsuarioRepository;
import senac.edu.engsoft.meuproduto.service.util.CpfValidatorUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario save(Usuario usuario) {
		Long usuarioCpf = usuario.getCpf();
		String usuarioEmail = usuario.getUsername();

		if(!CpfValidatorUtil.isValidCpf(Long.toString(usuarioCpf))) {
			throw new ObjectCreationValidationException("CPF com formato inválido: " + usuarioCpf);
		}
		else if (usuarioRepository.getByCpf(usuarioCpf).isPresent()){
			throw new ObjectCreationValidationException("CPF inválido: " + usuarioCpf);
		}
		else if (usuarioRepository.getByEmail(usuarioEmail).isPresent()){
			throw new ObjectCreationValidationException("E-mail indisponível: " + usuarioEmail);
		}
		else {
			return usuarioRepository.save(usuario);
		}
	}
}
