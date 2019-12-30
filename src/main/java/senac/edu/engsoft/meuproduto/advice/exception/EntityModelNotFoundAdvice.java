package senac.edu.engsoft.meuproduto.advice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityModelNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(EntityModelNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String lojaNotFoundHandler(EntityModelNotFoundException e) {
		return e.getMessage();
	}
}
