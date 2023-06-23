package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.UsuarioGrupoFiltroRequest;
import italo.xclin.model.request.save.UsuarioGrupoSaveRequest;

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
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		
	}
	
}

