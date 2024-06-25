package italo.xclin.service.naoadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalEspecialidadeVinculo;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.request.filtro.NaoAdminProfissionalFiltroRequest;
import italo.xclin.model.response.ProfissionalResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminProfissionalTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class NaoAdminProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProfissionalLoader profissionalLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;

	public List<ProfissionalResponse> filtra( Long clinicaId, NaoAdminProfissionalFiltroRequest request ) throws ServiceException {
		String filtroNome = request.getFiltroNome();
		
		List<Profissional> profissionais;
		if ( filtroNome.equals( "*" ) ) {
			profissionais = profissionalRepository.listaPorClinica( clinicaId );
		} else {		
			profissionais = profissionalRepository.filtra( clinicaId, filtroNome+"%" );
		}
		
		List<ProfissionalResponse> lista = new ArrayList<>();
		for( Profissional d : profissionais ) {
			Usuario u = d.getUsuario();
			
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, u );
			
			ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
			profissionalLoader.loadResponse( resp, d );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public NaoAdminProfissionalTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return profissionalLoader.novoNaoAdminTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoad( Long profissionalId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.busca( profissionalId, clinicasIDs );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		Usuario u = p.getUsuario();
		
		List<String> clinicas = new ArrayList<>();
		
		List<UsuarioClinicaVinculo> pcVinculos = u.getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo v : pcVinculos )
			clinicas.add( v.getClinica().getNome() );
		
		List<String> especialidades = new ArrayList<>();
		
		List<ProfissionalEspecialidadeVinculo> peVinculos = p.getProfissionalEspecialidadeVinculos();
		for( ProfissionalEspecialidadeVinculo v : peVinculos )
			especialidades.add( v.getEspecialidade().getNome() );		
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, u );
		
		ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( resp, p );
		
		return profissionalLoader.novoDetalhesResponse( resp, clinicas, especialidades ); 
	}
	
}
