package italo.xclin.exception;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import italo.xclin.Erro;
import italo.xclin.model.response.ErroResponse;

@ControllerAdvice
public class SistemaExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {
		ServiceException.class, 
		ValidationException.class,
		ConverterException.class,
		AutorizacaoException.class
	} )
	protected ResponseEntity<Object> handle( Exception e, WebRequest request ) {
		return ResponseEntity.badRequest().body( new ErroResponse( (SistemaException)e ) );
	}

	@ExceptionHandler(SizeLimitExceededException.class)
	protected ResponseEntity<Object> handleFileSizeLimit( SizeLimitExceededException e ) {
		int sizeInMB = (int)( e.getPermittedSize()/1024000 );

		SistemaException ex = new SistemaException( Erro.TOMCAT_FILE_SIZE_LIMIT_EXCEEDED, ""+sizeInMB+" MB " );

		return ResponseEntity.badRequest().body( new ErroResponse( ex ) );
	}
	
}
