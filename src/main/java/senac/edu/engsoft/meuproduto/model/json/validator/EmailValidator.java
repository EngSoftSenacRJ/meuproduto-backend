package senac.edu.engsoft.meuproduto.model.json.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = EmailValidatorImpl.class)
public @interface EmailValidator {

    String message() default "Email inválido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
