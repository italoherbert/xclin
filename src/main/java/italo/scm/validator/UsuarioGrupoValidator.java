package italo.scm.validator;

import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.UsuarioGrupoFiltroRequest;
import italo.scm.model.request.save.UsuarioGrupoSaveRequest;

@Component
public class UsuarioGrupoValidator {
		
	public void validaSave( UsuarioGrupoSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.USUARIO_GRUPO_NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.USUARIO_GRUPO_NOME_OBRIGATORIO );		
	}
	
	public void validaFiltro( UsuarioGrupoFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.USUARIO_GRUPO_NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.USUARIO_GRUPO_NOME_INI_OBRIGATORIO );
		
	}
	
}

