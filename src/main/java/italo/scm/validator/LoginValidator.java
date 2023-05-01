package italo.scm.validator;

import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.LoginRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
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
