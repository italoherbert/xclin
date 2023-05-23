package italo.scm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.ConsultaStatusEnumManager;
import italo.scm.enums.TurnoEnumManager;
import italo.scm.enums.tipos.ConsultaStatus;
import italo.scm.enums.tipos.Turno;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ConsultaLoader;
import italo.scm.loader.EspecialidadeLoader;
import italo.scm.logica.Converter;
import italo.scm.model.Clinica;
import italo.scm.model.Consulta;
import italo.scm.model.Especialidade;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.filtro.ConsultaFilaFiltroRequest;
import italo.scm.model.request.filtro.ConsultaFiltroRequest;
import italo.scm.model.request.save.ConsultaAlterSaveRequest;
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.EspecialidadeResponse;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.scm.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.scm.model.response.load.outros.NovaConsultaProfissionalSelectLoadResponse;
import italo.scm.model.response.load.reg.ConsultaRegLoadResponse;
import italo.scm.model.response.load.tela.ConsultaFilaTelaLoadResponse;
import italo.scm.model.response.load.tela.ConsultaTelaLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.ConsultaRepository;
import italo.scm.repository.EspecialidadeRepository;
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
	private EspecialidadeRepository especialidadeRepository;

	@Autowired
	private ClinicaSharedService clinicaSharedService;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	@Autowired
	private EspecialidadeLoader especialidadeLoader;
	
	@Autowired
	private Converter converter;

	@Autowired
	private ConsultaStatusEnumManager consultaStatusEnumManager;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	public void registra( 
			Long clinicaId, 
			Long profissionalId,
			Long especialidadeId, 
			Long pacienteId, 
			ConsultaSaveRequest request ) throws ServiceException {
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
		if ( !especialidadeOp.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Clinica clinica = clinicaOp.get();
		Profissional profissional = profissionalOp.get();
		Paciente paciente = pacienteOp.get();
		Especialidade especialidade = especialidadeOp.get();
		
		Consulta consulta = consultaLoader.novoBean( profissional, especialidade, paciente, clinica );
		consultaLoader.loadBean( consulta, request );
		
		consultaRepository.save( consulta );
	}
	
	public void altera( Long consultaId, ConsultaAlterSaveRequest request ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();		
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
	
	public void registraPagamento( Long consultaId ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
				
		Consulta consulta = consultaOp.get();
		consulta.setPaga( true );
		
		consultaRepository.save( consulta );		
	}
	
	public void finalizaConsulta( Long consultaId ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consulta.setStatus( ConsultaStatus.FINALIZADA );
		
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
			Especialidade e = c.getEspecialidade();
			ConsultaResponse resp = consultaLoader.novoResponse( p, cl, e );
			consultaLoader.loadResponse( resp, c );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public List<ConsultaResponse> listaFila( 
			Long clinicaId, 
			Long profissionalId, 
			ConsultaFilaFiltroRequest request ) throws ServiceException {
		
		Date data = converter.stringToDataNEx( request.getData() );
		Turno turno = turnoEnumManager.getEnum( request.getTurno() );
		ConsultaStatus status = consultaStatusEnumManager.getEnum( request.getStatus() );
		
		List<Consulta> fila = consultaRepository.listaFila(
				clinicaId, profissionalId, data, turno, status );
		
		List<ConsultaResponse> lista = new ArrayList<>();
		for( Consulta c : fila ) {
			Paciente p = c.getPaciente();
			Clinica cl = c.getClinica();
			Especialidade e = c.getEspecialidade();
			ConsultaResponse resp = consultaLoader.novoResponse( p, cl, e );
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
		Especialidade especialidade = consulta.getEspecialidade();
		
		ConsultaResponse resp = consultaLoader.novoResponse( paciente, clinica, especialidade );
		consultaLoader.loadResponse( resp, consulta );
		
		return resp;
	}
	
	public ConsultaRegLoadResponse getRegLoad( Long profissionalId ) {
		List<Especialidade> especialidades = especialidadeRepository.listaPorProfissional( profissionalId );
		
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		
		ConsultaRegLoadResponse resp = consultaLoader.novoRegResponse( lista );
		consultaLoader.loadRegResponse( resp );
		return resp;
	}
	
	public ConsultaRemarcarLoadResponse getRemarcarLoad( Long consultaId ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		
		ConsultaRemarcarLoadResponse resp = consultaLoader.novoRemarcarResponse( consulta );
		consultaLoader.loadRemarcarResponse( resp );
		return resp;
	}
	
	public ConsultaAlterLoadResponse getAlterLoad( Long consultaId ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		Paciente paciente = consulta.getPaciente();
		Clinica clinica = consulta.getClinica();
		Especialidade especialidade = consulta.getEspecialidade();
		
		ConsultaResponse cresp = consultaLoader.novoResponse( paciente, clinica, especialidade );
		consultaLoader.loadResponse( cresp, consulta );
		
		ConsultaAlterLoadResponse resp = consultaLoader.novoAlterResponse( cresp );
		consultaLoader.loadAlterResponse( resp );
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
	
	public ConsultaFilaTelaLoadResponse getFilaTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		ConsultaFilaTelaLoadResponse resp = consultaLoader.novoFilaTelaResponse( clinicasIDs2, clinicasNomes2 );
		consultaLoader.loadFilaTelaResponse( resp );
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
			
	public void deleta( Long consultaId ) throws ServiceException {
		boolean existe = consultaRepository.existsById( consultaId );
		if ( !existe )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		consultaRepository.deleteById( consultaId ); 
	}
}
