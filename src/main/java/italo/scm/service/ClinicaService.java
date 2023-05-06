package italo.scm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ClinicaLoader;
import italo.scm.loader.EnderecoLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Endereco;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.UsuarioRepository;

@Service
public class ClinicaService {
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClinicaLoader clinicaLoader;
	
	@Autowired
	private EnderecoLoader enderecoLoader;
	
	public void registra( Long uid, ClinicaSaveRequest request ) throws ServiceException {
		boolean existe = clinicaRepository.existePorNome( request.getNome() );
		if ( existe )
			throw new ServiceException( Erro.CLINICA_JA_EXISTE );
		
		Optional<Usuario> uop = usuarioRepository.findById( uid );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = uop.get();
		
		Endereco ender = enderecoLoader.novoBean();
		enderecoLoader.loadBean(ender, request.getEndereco() ); 
		
		Clinica clinica = clinicaLoader.novoBean( ender, usuarioLogado );
		clinicaLoader.loadBean( clinica, request );
		
		clinicaRepository.save( clinica );
	}

}
