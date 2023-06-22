package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.LoginRequest;
import italo.xclin.msg.Erro;

@Component
public class LoginValidator {
	
	public void validaLogin( LoginRequest request ) throws ValidationException {
		if ( request.getUsername() == null )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		if ( request.getUsername().isBlank() )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		if ( request.getSenha() == null )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );
		if ( request.getSenha().isBlank() )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );
	}
	
}
