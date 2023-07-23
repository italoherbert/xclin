package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.NaoAdminRecepcionistaFiltroRequest;
import italo.xclin.model.request.filtro.RecepcionistaFiltroRequest;
import italo.xclin.model.request.save.RecepcionistaSaveRequest;

@Component
public class RecepcionistaValidator {

	@Autowired
	private UsuarioValidator usuarioValidator;
	
	public void validaSave( RecepcionistaSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getUsuario() == null )
			throw new ValidationException( Erro.USUARIO_NULO );
				
		usuarioValidator.validaSave( request.getUsuario() ); 
	}
	
	public void validaFiltro( RecepcionistaFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );			
	}
	
	public void validaNaoAdminFiltro( NaoAdminRecepcionistaFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );			
	}
	
}
