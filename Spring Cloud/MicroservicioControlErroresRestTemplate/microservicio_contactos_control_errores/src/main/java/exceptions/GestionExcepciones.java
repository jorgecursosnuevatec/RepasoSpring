package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GestionExcepciones {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manejaError(Exception e) {
		//se devuelve una respuesta con el código de error BAD_REQUEST
		//indicando en el cuerpo el mensaje de error
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
