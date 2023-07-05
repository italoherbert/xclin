package italo.xclin.service.shared;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.ServiceException;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioGrupo;
import italo.xclin.model.UsuarioGrupoVinculo;
import italo.xclin.repository.UsuarioGrupoRepository;
import italo.xclin.repository.UsuarioGrupoVinculoRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioSharedService {

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
		
	@Autowired
	private UsuarioGrupoVinculoRepository usuarioGrupoVinculoRepository;
	
	@Transactional
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
