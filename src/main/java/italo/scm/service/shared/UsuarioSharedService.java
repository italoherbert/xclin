package italo.scm.service.shared;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.UsuarioGrupoVinculo;
import italo.scm.repository.UsuarioGrupoRepository;
import italo.scm.repository.UsuarioGrupoVinculoRepository;

@Service
public class UsuarioSharedService {

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
		
	@Autowired
	private UsuarioGrupoVinculoRepository usuarioGrupoVinculoRepository;
	
	public void vinculaGrupo( Usuario usuario, UsuarioPerfil perfil ) throws ServiceException {				
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.buscaPorNome( perfil.name() );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		
		UsuarioGrupoVinculo vinculo = new UsuarioGrupoVinculo();
		vinculo.setUsuario( usuario ); 
		vinculo.setGrupo( grupo );
		
		usuarioGrupoVinculoRepository.save( vinculo );	
	}
	
}
