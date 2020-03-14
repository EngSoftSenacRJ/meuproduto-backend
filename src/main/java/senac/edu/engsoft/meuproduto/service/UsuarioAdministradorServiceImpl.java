package senac.edu.engsoft.meuproduto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.repository.UsuarioAdministradorRepository;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
	public Optional<UsuarioAdministrador> getByUsername(String username) {
		return usuarioAdministradorRepository.getByUsername(username);
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
	
	@Override
	public UsuarioAdministrador update(Long id, UsuarioAdministrador usuarioAdministrador) {
		Optional<UsuarioAdministrador> usuarioAdministradorEncontrado = getById(id);
		UsuarioAdministrador usuarioAdministradorParaAtualizar = usuarioAdministradorEncontrado.get();
		usuarioAdministradorEncontrado.get().copyForNew(usuarioAdministrador);
		return usuarioAdministradorRepository.save(usuarioAdministradorParaAtualizar); //update
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


	public void copyNonNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(target));
	}

	public String[] getNullPropertyNames (Object source) {

		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] propDesList = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();

		for(PropertyDescriptor propDesc : propDesList) {
			Object srcValue = src.getPropertyValue(propDesc.getName());

			if (srcValue == null) {
				emptyNames.add(propDesc.getName());
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
