package senac.edu.engsoft.meuproduto.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

//@Component("beforeCreateUsuarioAdministradorValidator")
public class UsuarioAdministradorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioAdministrador.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsuarioAdministrador usuarioAdministrador = (UsuarioAdministrador) target;
		if(StringUtils.isEmpty(usuarioAdministrador.getCpf())) {
			errors.rejectValue("cpf", "cpf.empty");
		}
		if(StringUtils.isEmpty(usuarioAdministrador.getEmail())) {
			errors.rejectValue("email", "email.empty");
		}
		if(StringUtils.isEmpty(usuarioAdministrador.getNome())) {
			errors.rejectValue("nome", "nome.empty");
		}
		if(StringUtils.isEmpty(usuarioAdministrador.getTelefoneContato())) {
			errors.rejectValue("telefoneContato", "telefoneContato.empty");
		}
	}
}
