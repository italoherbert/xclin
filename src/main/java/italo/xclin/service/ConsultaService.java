package italo.xclin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.Info;
import italo.xclin.enums.ConsultaStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.enums.tipos.ConsultaStatus;
import italo.xclin.enums.tipos.LancamentoTipo;
import italo.xclin.enums.tipos.Turno;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ConsultaLoader;
import italo.xclin.loader.EspecialidadeLoader;
import italo.xclin.loader.LancamentoLoader;
import italo.xclin.loader.PacienteAnexoLoader;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Consulta;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.Profissional;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.ConsultaFiltroRequest;
import italo.xclin.model.request.filtro.ConsultaListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.ConsultaListaFilaFiltroRequest;
import italo.xclin.model.request.save.ConsultaAlterSaveRequest;
import italo.xclin.model.request.save.ConsultaObservacoesSaveRequest;
import italo.xclin.model.request.save.ConsultaRemarcarSaveRequest;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaIniciadaResponse;
import italo.xclin.model.response.ConsultaObservacoesResponse;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.xclin.model.response.load.outros.ConsultaAgendaLoadResponse;
import italo.xclin.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.xclin.model.response.load.outros.NovaConsultaLoadResponse;
import italo.xclin.model.response.load.reg.ConsultaRegLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.ConsultaRepository;
import italo.xclin.repository.EspecialidadeRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.repository.ProfissionalRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.ClinicaSharedService;
import jakarta.transaction.Transactional;

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
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	@Autowired
	private EspecialidadeLoader especialidadeLoader;
	
	@Autowired
	private LancamentoLoader lancamentoLoader;
	
	@Autowired
	private PacienteAnexoLoader pacienteAnexoLoader;
	
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
		
		ConsultaStatus status = consultaStatusEnumManager.getEnum( request.getStatus() );		
		if ( status == ConsultaStatus.INICIADA )
			throw new ServiceException( Erro.NAO_PODE_INICIAR_CONSULTA );
		
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
	
	@Transactional
	public void setaPagamento( Long logadoUID, Long consultaId, boolean paga ) throws ServiceException {				
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
				
		Consulta consulta = consultaOp.get();
		consulta.setPaga( paga );			
		
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		consultaRepository.save( consulta );
					
		Clinica c = consulta.getClinica();
		Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, c );
		lanc.setDataLancamento( new Date() );
		if ( paga ) {			
			lanc.setTipo( LancamentoTipo.CREDITO );
			lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );
		} else {
			lanc.setTipo( LancamentoTipo.DEBITO ); 
			lanc.setObservacoes( Info.PAGAMENTO_DEBITADO ); 
		}
		lanc.setValor( consulta.getValor() ); 
		
		lancamentoRepository.save( lanc );
	}
		
	public void cancelaConsulta( Long consultaId ) throws ServiceException {
		this.alteraStatus( consultaId, ConsultaStatus.CANCELADA ); 
	}
	
	public void finalizaConsulta( Long consultaId ) throws ServiceException {
		this.alteraStatus( consultaId, ConsultaStatus.FINALIZADA );
	}
	
	public void alteraStatus( Long consultaId, ConsultaStatus status ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consulta.setStatus( status );
		
		consultaRepository.save( consulta );
	}
	
	@Transactional
	public void iniciaConsulta( Long clinicaId, Long profissionalId, Long consultaId, String turnoStr ) throws ServiceException {
		boolean existeClinica = clinicaRepository.existsById( clinicaId );
		if ( !existeClinica )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		boolean existeProfissional = profissionalRepository.existsById( profissionalId );
		if ( !existeProfissional )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Turno turno = turnoEnumManager.getEnum( turnoStr );		
		
		consultaRepository.finalizaConsultasIniciadas( clinicaId, profissionalId, turno );
				
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consulta.setStatus( ConsultaStatus.INICIADA );
		
		consultaRepository.save( consulta );
	}
	
	public void salvaObservacoes( Long consultaId, ConsultaObservacoesSaveRequest request ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consultaLoader.loadBean( consulta, request ); 
		
		consultaRepository.save( consulta );
	}
	
	public ConsultaIniciadaResponse getIniciada( 
			Long logadoUID, Long clinicaId, String turnoStr, int histObsPageSize ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Turno turno = turnoEnumManager.getEnum( turnoStr );		
		
		Optional<Consulta> consultaOp = consultaRepository.getIniciada( clinicaId, profissionalId, turno );
				
		int quantPacientesNaFila = consultaRepository.contaFila( clinicaId, profissionalId, turno );

		if ( consultaOp.isPresent() ) {						
			Consulta consulta = consultaOp.get();
			Clinica c = consulta.getClinica();
			Profissional pr = consulta.getProfissional();
			Paciente pa = consulta.getPaciente();
			Especialidade e = consulta.getEspecialidade();
						
			ConsultaResponse cresp = consultaLoader.novoResponse( c, pr, pa, e );
			consultaLoader.loadResponse( cresp, consulta );
			
			Long pacienteId = pa.getId();
			
			List<ConsultaObservacoesResponse> historicoObservacoes = this.getUltimasObservacoes( 
					clinicaId, profissionalId, pacienteId, histObsPageSize );			
			
			List<PacienteAnexo> anexos = pa.getAnexos();
			
			List<PacienteAnexoResponse> respAnexos = new ArrayList<>();
			for( PacienteAnexo a : anexos ) {
				PacienteAnexoResponse aresp = pacienteAnexoLoader.novoResponse();
				pacienteAnexoLoader.loadResponse( aresp, a );
				
				respAnexos.add( aresp );
			}
			
			return consultaLoader.novoIniciadaResponse( 
					cresp, historicoObservacoes, respAnexos, quantPacientesNaFila );
		}
		
		return consultaLoader.novoNenhumaIniciadaResponse( quantPacientesNaFila );		
	}
	
	private List<ConsultaObservacoesResponse> getUltimasObservacoes( 
			Long clinicaId, Long profissionalId, Long pacienteId, int pageSize ) throws ServiceException {
		
		List<Consulta> consultas = consultaRepository.getUltimasObservacoes(
				clinicaId, profissionalId, pacienteId, PageRequest.of( 0, pageSize ) );
		
		List<ConsultaObservacoesResponse> lista = new ArrayList<>();
		for( Consulta c : consultas ) {
			ConsultaObservacoesResponse resp = consultaLoader.novoObservacoesResponse( c );
			lista.add( resp );
		}
		return lista;
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
		for( Consulta consulta : consultas ) {
			Clinica c = consulta.getClinica();
			Profissional pr = consulta.getProfissional();
			Paciente pa = consulta.getPaciente();
			Especialidade e = consulta.getEspecialidade();
						
			ConsultaResponse resp = consultaLoader.novoResponse( c, pr, pa, e );
			consultaLoader.loadResponse( resp, consulta );
			
			lista.add( resp );
		}
		return lista;
	}
	
	/*
	 * Fila incluíndo qualquer status
	 * */
	public List<ConsultaResponse> listaFilaCompleta(
			Long clinicaId, 
			Long profissionalId, 
			ConsultaListaFilaCompletaFiltroRequest request ) throws ServiceException {
		
		Date data = converter.stringToDataNEx( request.getData() );
		Turno turno = turnoEnumManager.getEnum( request.getTurno() );
		
		List<Consulta> fila = consultaRepository.listaFilaCompleta(
				clinicaId, profissionalId, data, turno );
		
		List<ConsultaResponse> lista = new ArrayList<>();
		for( Consulta consulta : fila ) {
			Clinica c = consulta.getClinica();
			Profissional pr = consulta.getProfissional();
			Paciente pa = consulta.getPaciente();
			Especialidade e = consulta.getEspecialidade();
						
			ConsultaResponse resp = consultaLoader.novoResponse( c, pr, pa, e );
			consultaLoader.loadResponse( resp, consulta );
			
			lista.add( resp );
		}
		return lista;
	}
		
	/*
	 * Fila incluíndo filtro por status
	 * */
	public List<ConsultaResponse> listaFila( 
			Long clinicaId, 
			Long profissionalId, 
			ConsultaListaFilaFiltroRequest request ) throws ServiceException {
		
		Date data = converter.stringToDataNEx( request.getData() );
		Turno turno = turnoEnumManager.getEnum( request.getTurno() );
		ConsultaStatus status = consultaStatusEnumManager.getEnum( request.getStatus() );
		
		List<Consulta> fila = consultaRepository.listaFilaPorStatus(
				clinicaId, profissionalId, data, turno, status );
		
		List<ConsultaResponse> lista = new ArrayList<>();
		for( Consulta consulta : fila ) {
			Clinica c = consulta.getClinica();
			Profissional pr = consulta.getProfissional();
			Paciente pa = consulta.getPaciente();
			Especialidade e = consulta.getEspecialidade();
						
			ConsultaResponse resp = consultaLoader.novoResponse( c, pr, pa, e );
			consultaLoader.loadResponse( resp, consulta );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public ConsultaResponse get( Long id ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( id );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		Clinica c = consulta.getClinica();
		Profissional pr = consulta.getProfissional();
		Paciente pa = consulta.getPaciente();
		Especialidade e = consulta.getEspecialidade();
					
		ConsultaResponse resp = consultaLoader.novoResponse( c, pr, pa, e );
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
		Clinica clinica = consulta.getClinica();
		Profissional profissional = consulta.getProfissional();
		Paciente paciente = consulta.getPaciente();
		Especialidade especialidade = consulta.getEspecialidade();
		
		ConsultaResponse cresp = consultaLoader.novoResponse( clinica, profissional, paciente, especialidade );
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
	
	public ConsultaListaFilaTelaLoadResponse getFiltroResumidoTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		ConsultaListaFilaTelaLoadResponse resp = consultaLoader.novoFilaTelaResponse( clinicasIDs2, clinicasNomes2 );
		consultaLoader.loadListaFilaTelaResponse( resp );
		return resp;
	}
	
	public ConsultaIniciadaTelaLoadResponse getIniciadaTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		ConsultaIniciadaTelaLoadResponse resp = consultaLoader.novoIniciadaTelaResponse( clinicasIDs2, clinicasNomes2 );
		consultaLoader.loadIniciadaTelaResponse( resp );
		return resp;
	}
	
	public NovaConsultaLoadResponse getNovaConsultaLoad( Long[] clinicasIDs ) {
		ListaResponse resp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		return consultaLoader.novoNovaConsultaLoadResponse( resp.getIds(), resp.getNomes() ); 
	}
	
	public ConsultaAgendaLoadResponse getConsultaAgendaLoad( Long[] clinicasIDs ) {
		ListaResponse lresp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		ConsultaAgendaLoadResponse resp = consultaLoader.novoConsultaAgendaLoadResponse( lresp.getIds(), lresp.getNomes() );
		consultaLoader.loadConsultaAgendaResponse( resp ); 
		return resp;
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
