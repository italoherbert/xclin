package italo.xclin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import italo.xclin.model.response.ErroResponse;

@ControllerAdvice
public class SistemaExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {
		ServiceException.class, 
		ValidationException.class,
		ConverterException.class,
		AutorizadorException.class
	} )
	protected ResponseEntity<Object> handle( Exception e, WebRequest request ) {
		return ResponseEntity.badRequest().body( new ErroResponse( (SistemaException)e ) );
	}
	
}
