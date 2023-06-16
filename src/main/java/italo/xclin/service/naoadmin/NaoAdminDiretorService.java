package italo.xclin.service.naoadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.DiretorLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Diretor;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.request.filtro.NaoAdminDiretorFiltroRequest;
import italo.xclin.model.response.DiretorResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.DiretorDetalhesLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminDiretorTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.DiretorRepository;

@Service
public class NaoAdminDiretorService {
	
	@Autowired
	private DiretorRepository diretorRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private DiretorLoader diretorLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;

	public List<DiretorResponse> filtra( Long clinicaId, NaoAdminDiretorFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Diretor> diretores;
		if ( nomeIni.equals( "*" ) ) {
			diretores = diretorRepository.filtra( clinicaId );
		} else {		
			diretores = diretorRepository.filtra( clinicaId, nomeIni+"%" );
		}
		
		List<DiretorResponse> lista = new ArrayList<>();
		for( Diretor d : diretores ) {
			Usuario u = d.getUsuario();
			
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, u );
			
			DiretorResponse resp = diretorLoader.novoResponse( uresp );
			diretorLoader.loadResponse( resp, d );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public NaoAdminDiretorTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return diretorLoader.novoNaoAdminTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public DiretorDetalhesLoadResponse getDetalhesLoad( Long diretorId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.busca( diretorId, clinicasIDs );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Diretor d = diretorOp.get();
		Usuario u = d.getUsuario();
		
		List<String> clinicas = new ArrayList<>();
		
		List<UsuarioClinicaVinculo> vinculos = u.getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo v : vinculos )
			clinicas.add( v.getClinica().getNome() );
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, u );
		
		DiretorResponse resp = diretorLoader.novoResponse( uresp );
		diretorLoader.loadResponse( resp, d );
		
		return diretorLoader.novoDetalhesResponse( resp, clinicas ); 
	}
	
}
