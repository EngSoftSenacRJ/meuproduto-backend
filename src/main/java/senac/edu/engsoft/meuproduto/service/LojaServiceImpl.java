package senac.edu.engsoft.meuproduto.service;

import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.ObjectCreationValidationException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.repository.LojaRepository;
import senac.edu.engsoft.meuproduto.service.util.HorarioFuncionamentoLojaUtil;

import java.util.Optional;

@Service
public class LojaServiceImpl implements LojaService {

	private final LojaRepository lojaRepository;
	private final UsuarioAdministradorService usuarioAdministradorService;
	
	public LojaServiceImpl(LojaRepository lojaRepository,
						   UsuarioAdministradorService usuarioAdministradorService) {
		super();
		this.lojaRepository = lojaRepository;
		this.usuarioAdministradorService = usuarioAdministradorService;
	}

	@Override
	public Optional<Loja> getById(Long id) {
		return lojaRepository.findById(id);
	}
	
	@Override
	public Optional<Loja> getByNome(String nome) {
		return lojaRepository.getByNome(nome);
	}

	@Override
	public Loja save(Loja loja) {
		validateLoja(loja);
		HorarioFuncionamentoLojaUtil.setDefaultHorarioFuncionamentoLoja(loja);
		return lojaRepository.save(loja);
	}

	@Override
	public Loja update(Loja loja) {
		validateLoja(loja);
		return lojaRepository.save(loja);
	}

	@Override
	public void delete(Long id) {
		lojaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		lojaRepository.deleteAll();
	}

	@Override
	public Iterable<Loja> getAll() {
		return lojaRepository.findAll();
	}

	private void validateLoja(Loja loja){
		String nomeLoja = loja.getNome();
		Optional<Loja> lojaExistente = lojaRepository.getByNome(nomeLoja);
		if(lojaExistente.isPresent()){
			throw new ObjectCreationValidationException("Loja já existente com esse nome: " + nomeLoja);
		}

		String emailUsuarioCriadorLoja = loja.getEmailUsuarioCriadorLoja();
		Optional<UsuarioAdministrador> usuarioAdministradorCriadorLoja = usuarioAdministradorService.getByEmail(emailUsuarioCriadorLoja);
		if(!usuarioAdministradorCriadorLoja.isPresent() || !usuarioAdministradorCriadorLoja.get().isEnabled()){
			throw new ObjectCreationValidationException("Email do Administrador fornecido não encontrado ou conta se encontra desabilitada");
		}
	}
	
}
