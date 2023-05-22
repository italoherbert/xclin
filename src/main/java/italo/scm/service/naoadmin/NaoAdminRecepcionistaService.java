package italo.scm.service.naoadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.RecepcionistaLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Recepcionista;
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.NaoAdminRecepcionistaFiltroRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.tela.NaoAdminRecepcionistaTelaLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.RecepcionistaRepository;

@Service
public class NaoAdminRecepcionistaService {

	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private RecepcionistaLoader recepcionistaLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;

	public List<RecepcionistaResponse> filtra( Long clinicaId, NaoAdminRecepcionistaFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Recepcionista> recepcionistas;
		if ( nomeIni.equals( "*" ) ) {
			recepcionistas = recepcionistaRepository.filtra( clinicaId );
		} else {		
			recepcionistas = recepcionistaRepository.filtra( clinicaId, nomeIni+"%" );
		}
		
		List<RecepcionistaResponse> lista = new ArrayList<>();
		for( Recepcionista d : recepcionistas ) {
			Usuario u = d.getUsuario();
			Clinica c = d.getClinica();
			
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, u );
			
			RecepcionistaResponse resp = recepcionistaLoader.novoResponse( uresp, c );
			recepcionistaLoader.loadResponse( resp, d );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public NaoAdminRecepcionistaTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return recepcionistaLoader.novoNaoAdminTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public RecepcionistaResponse get( Long recepcionistaId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.busca( recepcionistaId, clinicasIDs );
		if ( !recepcionistaOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Recepcionista d = recepcionistaOp.get();
		Usuario u = d.getUsuario();
		Clinica c = d.getClinica();		
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, u );
		
		RecepcionistaResponse resp = recepcionistaLoader.novoResponse( uresp, c );
		recepcionistaLoader.loadResponse( resp, d );		
		return resp; 
	}
	
}

