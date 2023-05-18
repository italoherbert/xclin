package italo.scm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.ConsultaStatusEnumManager;
import italo.scm.enums.TurnoEnumManager;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ConsultaLoader;
import italo.scm.logica.Converter;
import italo.scm.model.Clinica;
import italo.scm.model.Consulta;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.filtro.ConsultaFiltroRequest;
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.load.ConsultaRegLoadResponse;
import italo.scm.model.response.load.ConsultaTelaLoadResponse;
import italo.scm.model.response.load.NovaConsultaProfissionalSelectLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.ConsultaRepository;
import italo.scm.repository.PacienteRepository;
import italo.scm.repository.ProfissionalRepository;
import italo.scm.service.shared.ClinicaSharedService;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ClinicaSharedService clinicaSharedService;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	@Autowired
	private Converter converter;

	@Autowired
	private ConsultaStatusEnumManager consultaStatusEnumManager;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	public void registra( Long clinicaId, Long profissionalId, Long pacienteId, ConsultaSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
			
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Clinica clinica = clinicaOp.get();
		Profissional profissional = profissionalOp.get();
		Paciente paciente = pacienteOp.get();
		
		Consulta consulta = consultaLoader.novoBean( profissional, paciente, clinica );
		consultaLoader.loadBean( consulta, request );
		
		consultaRepository.save( consulta );
	}
	
	public void remarca( Long consultaId, ConsultaRemarcarSaveRequest request ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consultaLoader.loadBean( consulta, request );
		
		consultaRepository.save( consulta );
	}
	
	public List<ConsultaResponse> filtra( Long clinicaId, ConsultaFiltroRequest request ) throws ServiceException {
		Date dataIni = converter.stringToDataNEx( request.getDataInicio() );
		Date dataFim = converter.stringToDataNEx( request.getDataFim() );
				
		List<Consulta> consultas = consultaRepository.filtra( 
				clinicaId, 
				request.getPacienteNomeIni()+"%", 
				request.getProfissionalNomeIni()+"%", 
				turnoEnumManager.getEnum( request.getTurno() ), 
				consultaStatusEnumManager.getEnum( request.getStatus() ), 
				request.isIncluirPaciente(), 
				request.isIncluirProfissional(), 
				request.isIncluirTodosTurnos(), 
				request.isIncluirTodosStatuses(), 
				request.isIncluirPagas(), 
				request.isIncluirRetornos(),
				dataIni, dataFim );
		
		List<ConsultaResponse> lista = new ArrayList<>();
		for( Consulta c : consultas ) {
			Paciente p = c.getPaciente();
			Clinica cl = c.getClinica();
			ConsultaResponse resp = consultaLoader.novoResponse( p, cl );
			consultaLoader.loadResponse( resp, c );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public ConsultaResponse get( Long id ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( id );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		Clinica clinica = consulta.getClinica();
		Paciente paciente = consulta.getPaciente();
					
		ConsultaResponse resp = consultaLoader.novoResponse( paciente, clinica );
		consultaLoader.loadResponse( resp, consulta );
		
		return resp;
	}
	
	public ConsultaRegLoadResponse getRegLoad() {
		ConsultaRegLoadResponse resp = consultaLoader.novoRegResponse();
		consultaLoader.loadRegResponse( resp );
		return resp;
	}
	
	public ConsultaTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		ConsultaTelaLoadResponse resp = consultaLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
		consultaLoader.loadTelaResponse( resp );
		return resp;
	}
	
	
	public NovaConsultaProfissionalSelectLoadResponse getNovaConsultaProfissionalSelectLoad( Long[] clinicasIDs ) {
		ListaResponse resp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		return consultaLoader.novoProfissionalSelectLoadResponse( resp.getIds(), resp.getNomes() ); 
	}
	
	public List<Object[]> agrupaPorDiaDeMes( Long consultaId, int mes, int ano ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );			
		
		Consulta consulta = consultaOp.get();
		Long clinicaId = consulta.getClinica().getId();
		Long profissionalId = consulta.getProfissional().getId();
				
		return this.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );
	}
	
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano ) throws ServiceException {

		return consultaRepository.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );		
	}
		
	public List<ConsultaResponse> listaPorDia( 
			Long clinicaId, Long profissionalId, int dia, int mes, int ano ) {
		
		List<ConsultaResponse> lista = new ArrayList<>();
		
		List<Consulta> consultas = consultaRepository.listaPorDia( clinicaId, profissionalId, dia, mes, ano );
		for( Consulta c : consultas ) {
			Paciente paciente = c.getPaciente();
			Clinica clinica = c.getClinica();
			
			ConsultaResponse resp = consultaLoader.novoResponse( paciente, clinica );
			consultaLoader.loadResponse( resp, c );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
	public void deleta( Long consultaId ) throws ServiceException {
		boolean existe = consultaRepository.existsById( consultaId );
		if ( !existe )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		consultaRepository.deleteById( consultaId ); 
	}
}
