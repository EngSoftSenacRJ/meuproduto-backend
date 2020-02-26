package senac.edu.engsoft.meuproduto.model.json.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidatorImpl implements ConstraintValidator<EmailValidator, String> {

    String emailRegex = "^(.+)@(.+)$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null) return false;
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
