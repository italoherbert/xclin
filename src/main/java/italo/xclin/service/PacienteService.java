package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.EnderecoLoader;
import italo.xclin.loader.PacienteLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Endereco;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.filtro.PacienteFiltroRequest;
import italo.xclin.model.request.save.PacienteSaveRequest;
import italo.xclin.model.response.EnderecoResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.PacienteResponse;
import italo.xclin.model.response.UFResponse;
import italo.xclin.model.response.load.detalhes.PacienteDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.PacienteEditLoadResponse;
import italo.xclin.model.response.load.reg.PacienteRegLoadResponse;
import italo.xclin.model.response.load.tela.PacienteTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.service.shared.LocalidadesSharedService;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
			
	@Autowired
	private PacienteLoader pacienteLoader;
	
	@Autowired
	private EnderecoLoader enderecoLoader;
	
	@Autowired
	private LocalidadesSharedService localidadesSharedService;
			
	public void registra( Long clinicaId, PacienteSaveRequest request ) throws ServiceException {		
		String nome = request.getNome();
		boolean existe = pacienteRepository.existePorNome( nome, clinicaId );
		if ( existe )
			throw new ServiceException( Erro.CLINICA_PACIENTE_JA_EXISTE );
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		Endereco endereco = enderecoLoader.novoBean();
		enderecoLoader.loadBean( endereco, request.getEndereco() );
		
		Paciente paciente = pacienteLoader.novoBean( endereco, clinica );
		pacienteLoader.loadBean( paciente, request );
		
		pacienteRepository.save( paciente );		
	}
	
	public void altera( Long clinicaId, Long pacienteId, PacienteSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		Clinica c = p.getClinica();
		Endereco e = p.getEndereco();
				
		if ( clinicaId != c.getId() )
			throw new ServiceException( Erro.PACIENTE_CLINICA_NAO_CORRESPONDEM );
				
		String nome = request.getNome();
		if ( !nome.equalsIgnoreCase( p.getNome() ) ) {
			boolean existe = pacienteRepository.existePorNome( nome, clinicaId );
			if ( existe )
				throw new ServiceException( Erro.CLINICA_PACIENTE_JA_EXISTE );
		}
		
		enderecoLoader.loadBean( e, request.getEndereco() );
		pacienteLoader.loadBean( p, request );
		
		pacienteRepository.save( p );
	}
	
	public List<PacienteResponse> filtra( Long clinicaId, PacienteFiltroRequest request ) throws ServiceException {		
		String nomeIni = request.getNomeIni();
		
		List<Paciente> pacientes;
		if ( nomeIni.equals( "*" ) ) {
			pacientes = pacienteRepository.filtraTodosDaClinica( clinicaId );
		} else {
			pacientes = pacienteRepository.filtra( nomeIni+"%", clinicaId );			
		}
		
		List<PacienteResponse> lista = new ArrayList<>();
		for( Paciente p : pacientes ) {
			Clinica c = p.getClinica();
			Endereco e = p.getEndereco();
						
			EnderecoResponse eresp = enderecoLoader.novoResponse();
			enderecoLoader.loadResponse( eresp, e );			
			
			PacienteResponse resp = pacienteLoader.novoResponse( eresp, c );
			pacienteLoader.loadResponse( resp, p );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public PacienteResponse get( Long pacienteId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.busca( pacienteId, clinicasIDs );
				
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
				
		Paciente p = pacienteOp.get();
		
		Clinica c = p.getClinica();
		Endereco e = p.getEndereco();
					
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, e );			
		
		PacienteResponse resp = pacienteLoader.novoResponse( eresp, c );
		pacienteLoader.loadResponse( resp, p );
		
		return resp;
	}		
	
	public PacienteEditLoadResponse getEditLoad( Long pacienteId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.busca( pacienteId, clinicasIDs );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		
		Clinica c = p.getClinica();
		Endereco e = p.getEndereco();
					
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, e );			
		
		PacienteResponse presp = pacienteLoader.novoResponse( eresp, c );
		pacienteLoader.loadResponse( presp, p );
		
		int codigoUf = e.getCodigoUf();
		List<UFResponse> ufs = localidadesSharedService.listaUFs();
		List<MunicipioResponse> municipios = localidadesSharedService.listaMunicipios( codigoUf );
		
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		PacienteEditLoadResponse resp = 
				pacienteLoader.novoEditLoadResponse( presp, ufs, municipios, clinicasIDs2, clinicasNomes2 );
		
		pacienteLoader.loadEditResponse( resp ); 
		
		return resp;
	}
	
	public PacienteDetalhesLoadResponse getDetalhesLoad( Long pacienteId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.busca( pacienteId, clinicasIDs );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		
		Clinica c = p.getClinica();
		Endereco e = p.getEndereco();
					
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, e );			
		
		PacienteResponse presp = pacienteLoader.novoResponse( eresp, c );
		pacienteLoader.loadResponse( presp, p );
		
		int codigoUf = e.getCodigoUf();
		int codigoMunicipio = e.getCodigoMunicipio();
		
		UFResponse uf = localidadesSharedService.getUfPorId( codigoUf );
		MunicipioResponse municipio = localidadesSharedService.getMunicipioPorId( codigoMunicipio );
		
		PacienteDetalhesLoadResponse resp = pacienteLoader.novoDetalhesLoadResponse( presp, uf, municipio );
		pacienteLoader.loadDetalhesResponse( resp, p ); 
		return resp;
	}
	
	public PacienteRegLoadResponse getRegLoad( Long[] clinicasIDs ) throws ServiceException {
		List<UFResponse> ufs = localidadesSharedService.listaUFs();

		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		PacienteRegLoadResponse resp = 
				pacienteLoader.novoRegLoadResponse( ufs, clinicasIDs2, clinicasNomes2 );
		
		pacienteLoader.loadRegResponse( resp );
		return resp;
	}
	
	public PacienteTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return pacienteLoader.novoTelaLoadResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ListaResponse listaPorNome( Long clinicaId, String nomeIni, int limit ) throws ServiceException {
		List<Paciente> pacientes = pacienteRepository.filtra( "%"+nomeIni+"%", clinicaId, PageRequest.of( 0, limit ) );
		
		List<Long> ids = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		for( Paciente p : pacientes ) {
			ids.add( p.getId() );
			nomes.add( p.getNome() );
		}
		
		return new ListaResponse( ids, nomes );
	}
		
	public void delete( Long pacienteId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.busca( pacienteId, clinicasIDs );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
		
		pacienteRepository.deleteById( pacienteId ); 
	}
	
}
