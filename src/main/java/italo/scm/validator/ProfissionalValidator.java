package italo.scm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.NaoAdminProfissionalFiltroRequest;
import italo.scm.model.request.filtro.ProfissionalFiltroRequest;
import italo.scm.model.request.save.ProfissionalSaveRequest;

@Component
public class ProfissionalValidator {

	@Autowired
	private UsuarioValidator usuarioValidator;
	
	public void validaSave( ProfissionalSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getUsuario() == null )
			throw new ValidationException( Erro.USUARIO_NULL );
				
		usuarioValidator.validaSave( request.getUsuario() ); 
	}
	
	public void validaFiltro( ProfissionalFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		
		if ( request.getClinicaNomeIni() == null )
			throw new ValidationException( Erro.CLINICA_NOME_INI_OBRIGATORIO );
		
	}
	
	public void validaNaoAdminFiltro( NaoAdminProfissionalFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );				
	}
	
}

