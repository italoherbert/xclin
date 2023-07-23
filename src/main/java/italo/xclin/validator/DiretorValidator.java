package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.DiretorFiltroRequest;
import italo.xclin.model.request.filtro.NaoAdminDiretorFiltroRequest;
import italo.xclin.model.request.save.DiretorSaveRequest;

@Component
public class DiretorValidator {

	@Autowired
	private UsuarioValidator usuarioValidator;
	
	public void validaSave( DiretorSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getUsuario() == null )
			throw new ValidationException( Erro.USUARIO_NULO );
		
		usuarioValidator.validaSave( request.getUsuario() ); 
	}
	
	public void validaFiltro( DiretorFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );		
	}
	
	public void validaNaoAdminFiltro( NaoAdminDiretorFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
	}
	
}
