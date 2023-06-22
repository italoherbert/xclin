package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.UsuarioPerfilEnumManager;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.UsuarioFiltroRequest;
import italo.xclin.model.request.save.UsuarioSaveRequest;
import italo.xclin.model.request.save.UsuarioSenhaSaveRequest;
import italo.xclin.msg.Erro;

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
	
	public void validaAlteraSenha( UsuarioSenhaSaveRequest request ) throws ValidationException {
		if ( request.getSenha() == null )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );
		if ( request.getSenha().isBlank() )
			throw new ValidationException( Erro.SENHA_OBRIGATORIA );
	}
	
	public void validaSave( UsuarioSaveRequest request ) throws ValidationException {
		if ( request.getUsername() == null )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		if ( request.getUsername().isBlank() )
			throw new ValidationException( Erro.USERNAME_OBRIGATORIO );
		
		if ( !request.isIgnorarSenha() ) {
			if ( request.getSenha() == null )
				throw new ValidationException( Erro.SENHA_OBRIGATORIA );
			if ( request.getSenha().isBlank() )
				throw new ValidationException( Erro.SENHA_OBRIGATORIA );
		}
	}
	
	public void validaFiltro( UsuarioFiltroRequest request ) throws ValidationException {
		if ( request.getUsernameIni() == null )
			throw new ValidationException( Erro.USERNAME_INI_OBRIGATORIO );
		if ( request.getUsernameIni().isBlank() )
			throw new ValidationException( Erro.USERNAME_INI_OBRIGATORIO );
		
	}
	
}
