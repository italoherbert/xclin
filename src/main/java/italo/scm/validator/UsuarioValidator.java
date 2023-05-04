package italo.scm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.UsuarioPerfilEnumManager;
import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.UsuarioFiltroRequest;
import italo.scm.model.request.save.UsuarioSaveRequest;

@Component
public class UsuarioValidator {
	
	@Autowired
	private UsuarioPerfilEnumManager usuarioPerfilEnumManager;
	
	public void validaRegistro( UsuarioSaveRequest request ) throws ValidationException {
		this.validaSave( request ); 
		
		if ( request.getPerfil() == null )
			throw new ValidationException( Erro.PERFIL_INVALIDO, request.getPerfil() );		
		if ( !usuarioPerfilEnumManager.enumValida( request.getPerfil() ) )
			throw new ValidationException( Erro.PERFIL_INVALIDO, request.getPerfil() );
	}
	
	public void validaSave( UsuarioSaveRequest request ) throws ValidationException {
		if ( request.getUsername() == null )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		if ( request.getUsername().isBlank() )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		if ( request.getSenha() == null )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );
		if ( request.getSenha().isBlank() )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );		
	}
	
	public void validaFiltro( UsuarioFiltroRequest request ) throws ValidationException {
		if ( request.getUsernameIni() == null )
			throw new ValidationException( Erro.USERNAME_INI_OBRIGATORIO );
		if ( request.getUsernameIni().isBlank() )
			throw new ValidationException( Erro.USERNAME_INI_OBRIGATORIO );
		
	}
	
}
