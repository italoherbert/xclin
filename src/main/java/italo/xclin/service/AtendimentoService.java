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
import italo.xclin.enums.AtendimentoStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.enums.tipos.AtendimentoStatus;
import italo.xclin.enums.tipos.LancamentoTipo;
import italo.xclin.enums.tipos.Turno;
import italo.xclin.exception.LoaderException;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AtendimentoLoader;
import italo.xclin.loader.ConsultaLoader;
import italo.xclin.loader.EspecialidadeLoader;
import italo.xclin.loader.ExameItemLoader;
import italo.xclin.loader.LancamentoLoader;
import italo.xclin.loader.PacienteAnexoLoader;
import italo.xclin.loader.ProfissionalExameVinculoLoader;
import italo.xclin.logica.Converter;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Clinica;
import italo.xclin.model.Consulta;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Exame;
import italo.xclin.model.ExameItem;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalEspecialidadeVinculo;
import italo.xclin.model.ProfissionalExameVinculo;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.AtendimentoFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaFiltroRequest;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoPagamentoSaveRequest;
import italo.xclin.model.request.save.AtendimentoRemarcarSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.request.save.ExameItemSaveRequest;
import italo.xclin.model.response.AtendimentoIniciadoResponse;
import italo.xclin.model.response.AtendimentoObservacoesResponse;
import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.ExameItemResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoPagamentoLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoAgendaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoTelaLoadResponse;
import italo.xclin.repository.AtendimentoRepository;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.EspecialidadeRepository;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.repository.ProfissionalRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.ClinicaSharedService;
import jakarta.transaction.Transactional;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
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
	private ExameRepository exameRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private AtendimentoLoader atendimentoLoader;
	
	@Autowired
	private EspecialidadeLoader especialidadeLoader;
		
	@Autowired
	private LancamentoLoader lancamentoLoader;
	
	@Autowired
	private PacienteAnexoLoader pacienteAnexoLoader;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	@Autowired
	private ProfissionalExameVinculoLoader profissionalExameVinculoLoader;
	
	@Autowired
	private ExameItemLoader exameItemLoader;
	
	@Autowired
	private Converter converter;

	@Autowired
	private AtendimentoStatusEnumManager atendimentoStatusEnumManager;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	@Transactional
	public void registra( 
			Long logadoUID,
			Long clinicaId, 
			Long profissionalId,
			Long pacienteId, 
			AtendimentoSaveRequest request ) throws ServiceException {
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		Clinica clinica = clinicaOp.get();
		Profissional profissional = profissionalOp.get();
		Paciente paciente = pacienteOp.get();
		
		Consulta consulta = null;
		if ( request.isTemConsulta() ) {
			ConsultaSaveRequest consultaRequest = request.getConsulta();
			Long especialidadeId = consultaRequest.getEspecialidadeId();
			
			Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
			if ( !especialidadeOp.isPresent() )
				throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );

			Especialidade especialidade = especialidadeOp.get();
			
			consulta = consultaLoader.novoBean( especialidade );
			consultaLoader.loadBean( consulta, consultaRequest ); 
		}
		
		List<ExameItem> exames = new ArrayList<>();
		for( ExameItemSaveRequest reqExame : request.getExames() ) {
			Long exameId = reqExame.getExameId();
			
			Optional<Exame> exameOp = exameRepository.findById( exameId );
			if ( !exameOp.isPresent() )
				throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
			
			Exame exame = exameOp.get();
			ExameItem exameItem = exameItemLoader.novoBean( exame );
			try {
				exameItemLoader.loadBean( exameItem, reqExame );
			} catch (LoaderException ex) {
				ex.throwServiceException();
			}
			
			exames.add( exameItem );
		}
						
		Atendimento atendimento = atendimentoLoader.novoBean( 
				profissional, 
				paciente, 
				clinica, 
				consulta, 
				exames );
		atendimentoLoader.loadBean( atendimento, request );
		atendimento.setStatus( AtendimentoStatus.REGISTRADO ); 
		
		atendimentoRepository.save( atendimento );
		
		if ( request.isPago() ) {					
			Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, clinica );		
			lanc.setDataLancamento( new Date() );
			lanc.setTipo( LancamentoTipo.CREDITO );
			lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );
			
			lanc.setValor( request.getValorPago() );
			
			lancamentoRepository.save( lanc );
		}
	}
	
	public void altera( Long atendimentoId, AtendimentoAlterSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
				
		Atendimento atendimento = atendimentoOp.get();		
		atendimentoLoader.loadBean( atendimento, request );
		
		atendimentoRepository.save( atendimento );
	}
	
	public void remarca( Long atendimentoId, AtendimentoRemarcarSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		atendimentoLoader.loadBean( atendimento, request );
		
		atendimentoRepository.save( atendimento );
	}
	
	@Transactional
	public void efetuaPagamento( Long logadoUID, Long atendimentoId, AtendimentoPagamentoSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
						
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		Atendimento atendimento = atendimentoOp.get();
		atendimentoLoader.loadBean( atendimento, request ); 
		
		atendimentoRepository.save( atendimento );
		
		Clinica c = atendimento.getClinica();
		
		Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, c );
		lanc.setDataLancamento( new Date() );
		lanc.setTipo( LancamentoTipo.CREDITO );
		lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );		
		lanc.setValor( atendimento.getValorPago() ); 
		
		lancamentoRepository.save( lanc );
	}	
	
	@Transactional
	public void desfazPagamento( Long logadoUID, Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
						
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		Atendimento atendimento = atendimentoOp.get();
		
		double valorPago = atendimento.getValorPago();
				
		atendimento.setPago( false );
		atendimento.setValorPago( 0 );
		
		atendimentoRepository.save( atendimento );
		
		Clinica clinica = atendimento.getClinica();
		
		Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, clinica );
		lanc.setClinica( clinica );
		lanc.setUsuario( usuarioLogado ); 
		lanc.setDataLancamento( new Date() );
		lanc.setTipo( LancamentoTipo.DEBITO );
		lanc.setValor( valorPago );
		lanc.setObservacoes( Info.PAGAMENTO_DEBITADO );
		
		lancamentoRepository.save( lanc );
	}
		
	@Transactional
	public void cancelaAtendimento( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		if ( atendimento.isPago() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_CANCELADO );
		
		atendimento.setStatus( AtendimentoStatus.CANCELADO );		
		atendimentoRepository.save( atendimento );
	}
	
	public void finalizaAtendimento( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		atendimento.setStatus( AtendimentoStatus.FINALIZADO );
		
		atendimentoRepository.save( atendimento );
	}
	
	public void setaParaRegistrado( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();		
		atendimento.setStatus( AtendimentoStatus.REGISTRADO );
		
		atendimentoRepository.save( atendimento );
	}
	
	public void setaParaEsperando( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();		
		atendimento.setStatus( AtendimentoStatus.ESPERANDO );
		atendimento.setDataEspera( new Date() ); 
		
		atendimentoRepository.save( atendimento );
	}
	
	@Transactional
	public void iniciaAtendimento( Long clinicaId, Long profissionalId, Long atendimentoId, String turnoStr ) throws ServiceException {
		boolean existeClinica = clinicaRepository.existsById( clinicaId );
		if ( !existeClinica )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		boolean existeProfissional = profissionalRepository.existsById( profissionalId );
		if ( !existeProfissional )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Turno turno = turnoEnumManager.getEnum( turnoStr );		
		
		atendimentoRepository.finalizaAtendimentosIniciadas( clinicaId, profissionalId, turno );
				
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		atendimento.setStatus( AtendimentoStatus.INICIADO );
		
		atendimentoRepository.save( atendimento );
	}
	
	public void salvaObservacoes( Long atendimentoId, AtendimentoObservacoesSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		atendimentoLoader.loadBean( atendimento, request ); 
		
		atendimentoRepository.save( atendimento );
	}
		
	public AtendimentoIniciadoResponse getIniciado( 
			Long logadoUID, Long clinicaId, String turnoStr, int histObsPageSize ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Turno turno = turnoEnumManager.getEnum( turnoStr );		
		
		Optional<Atendimento> atendimentoOp = atendimentoRepository.getIniciado( clinicaId, profissionalId, turno );
				
		int quantPacientesNaFila = atendimentoRepository.contaFila( clinicaId, profissionalId, turno );

		if ( atendimentoOp.isPresent() ) {						
			Atendimento atendimento = atendimentoOp.get();
			Clinica clinica = atendimento.getClinica();
			Profissional profissional2 = atendimento.getProfissional();
			Paciente paciente = atendimento.getPaciente();
			
			Consulta consulta = atendimento.getConsulta();
			List<ExameItem> exames = atendimento.getExames();
			
			ConsultaResponse consultaResp = null;
			if ( atendimento.isTemConsulta() ) {
				Especialidade esp = consulta.getEspecialidade();
				consultaResp = consultaLoader.novoResponse( esp );
				consultaLoader.loadResponse( consultaResp, consulta );
			}
			
			List<ExameItemResponse> examesListaResp = new ArrayList<>();
			for( ExameItem ei : exames ) {
				ExameItemResponse eiResp = exameItemLoader.novoResponse();
				exameItemLoader.loadResponse( eiResp, ei );
				
				examesListaResp.add( eiResp );
			}
						
			AtendimentoResponse cresp = atendimentoLoader.novoResponse( 
					clinica,
					profissional2, 
					paciente, 
					consultaResp, 
					examesListaResp );
			
			atendimentoLoader.loadResponse( cresp, atendimento );
			
			Long pacienteId = paciente.getId();
			
			List<AtendimentoObservacoesResponse> historicoObservacoes = this.getUltimasObservacoes( 
					clinicaId, profissionalId, pacienteId, histObsPageSize );			
			
			List<PacienteAnexo> anexos = paciente.getAnexos();
			
			List<PacienteAnexoResponse> respAnexos = new ArrayList<>();
			for( PacienteAnexo a : anexos ) {
				PacienteAnexoResponse aresp = pacienteAnexoLoader.novoResponse();
				pacienteAnexoLoader.loadResponse( aresp, a );
				
				respAnexos.add( aresp );
			}
			
			return atendimentoLoader.novoIniciadoResponse( 
					cresp, historicoObservacoes, respAnexos, quantPacientesNaFila );
		}
		
		return atendimentoLoader.novoNenhumaIniciadaResponse( quantPacientesNaFila );		
	}
	
	private List<AtendimentoObservacoesResponse> getUltimasObservacoes( 
			Long clinicaId, Long profissionalId, Long pacienteId, int pageSize ) throws ServiceException {
		
		List<Atendimento> atendimentos = atendimentoRepository.getUltimasObservacoes(
				clinicaId, profissionalId, pacienteId, PageRequest.of( 0, pageSize ) );
		
		List<AtendimentoObservacoesResponse> lista = new ArrayList<>();
		for( Atendimento atendimento : atendimentos ) {
			AtendimentoObservacoesResponse resp = atendimentoLoader.novoObservacoesResponse( atendimento );
			lista.add( resp );
		}
		return lista;
	}
		
	public List<AtendimentoResponse> filtra( Long clinicaId, AtendimentoFiltroRequest request ) throws ServiceException {
		Date dataIni = converter.stringToDataNEx( request.getDataInicio() );
		Date dataFim = converter.stringToDataNEx( request.getDataFim() );
				
		List<Atendimento> atendimentos = atendimentoRepository.filtra( 
				clinicaId, 
				request.getPacienteNomeIni()+"%", 
				request.getProfissionalNomeIni()+"%", 
				turnoEnumManager.getEnum( request.getTurno() ), 
				atendimentoStatusEnumManager.getEnum( request.getStatus() ), 
				request.isIncluirPaciente(), 
				request.isIncluirProfissional(), 
				request.isIncluirTodosTurnos(), 
				request.isIncluirTodosStatuses(), 				
				dataIni, dataFim );
		
		List<AtendimentoResponse> lista = new ArrayList<>();
		for( Atendimento atendimento : atendimentos ) {
			Clinica clinica = atendimento.getClinica();
			Profissional profissional = atendimento.getProfissional();
			Paciente paciente = atendimento.getPaciente();
			
			Consulta consulta = atendimento.getConsulta();
			List<ExameItem> exames = atendimento.getExames();
			
			ConsultaResponse consultaResp = null;
			if ( atendimento.isTemConsulta() ) {
				Especialidade esp = consulta.getEspecialidade();
				consultaResp = consultaLoader.novoResponse( esp );
				consultaLoader.loadResponse( consultaResp, consulta );
			}
			
			List<ExameItemResponse> examesListaResp = new ArrayList<>();
			for( ExameItem ei : exames ) {
				ExameItemResponse eiResp = exameItemLoader.novoResponse();
				exameItemLoader.loadResponse( eiResp, ei );
				
				examesListaResp.add( eiResp );
			}
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( 
					clinica, profissional, paciente,
					consultaResp, examesListaResp );
			
			atendimentoLoader.loadResponse( resp, atendimento );
			
			lista.add( resp );
		}
		return lista;
	}
	
	/*
	 * Fila incluíndo qualquer status
	 * */
	public List<AtendimentoResponse> listaFilaCompleta(
			Long clinicaId, 
			Long profissionalId, 
			AtendimentoListaFilaCompletaFiltroRequest request ) throws ServiceException {
		
		Date data = converter.stringToDataNEx( request.getData() );
		Turno turno = turnoEnumManager.getEnum( request.getTurno() );
		
		List<Atendimento> fila = atendimentoRepository.listaFilaCompleta(
				clinicaId, profissionalId, data, turno );
		
		List<AtendimentoResponse> lista = new ArrayList<>();
		for( Atendimento atendimento : fila ) {
			Clinica clinica = atendimento.getClinica();
			Profissional profissional = atendimento.getProfissional();
			Paciente paciente = atendimento.getPaciente();
			
			Consulta consulta = atendimento.getConsulta();
			List<ExameItem> exames = atendimento.getExames();
			
			ConsultaResponse consultaResp = null;
			if ( atendimento.isTemConsulta() ) {
				Especialidade esp = consulta.getEspecialidade();
				consultaResp = consultaLoader.novoResponse( esp );
				consultaLoader.loadResponse( consultaResp, consulta );
			}
			
			List<ExameItemResponse> examesListaResp = new ArrayList<>();
			for( ExameItem ei : exames ) {
				ExameItemResponse eiResp = exameItemLoader.novoResponse();
				exameItemLoader.loadResponse( eiResp, ei );
				
				examesListaResp.add( eiResp );
			}
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( 
					clinica, profissional, paciente,
					consultaResp, examesListaResp );
			atendimentoLoader.loadResponse( resp, atendimento );
			
			lista.add( resp );
		}
		return lista;
	}
		
	/*
	 * Fila incluíndo filtro por status
	 * */
	public List<AtendimentoResponse> listaFila( 
			Long clinicaId, 
			Long profissionalId, 
			AtendimentoListaFilaFiltroRequest request ) throws ServiceException {
		
		Date data = converter.stringToDataNEx( request.getData() );
		Turno turno = turnoEnumManager.getEnum( request.getTurno() );
		AtendimentoStatus status = atendimentoStatusEnumManager.getEnum( request.getStatus() );
		
		List<Atendimento> fila = atendimentoRepository.listaFilaPorStatus(
				clinicaId, profissionalId, data, turno, status );
		
		List<AtendimentoResponse> lista = new ArrayList<>();
		for( Atendimento atendimento : fila ) {
			Clinica clinica = atendimento.getClinica();
			Profissional profissional = atendimento.getProfissional();
			Paciente paciente = atendimento.getPaciente();
			
			Consulta consulta = atendimento.getConsulta();
			List<ExameItem> exames = atendimento.getExames();
			
			ConsultaResponse consultaResp = null;
			if ( atendimento.isTemConsulta() ) {
				Especialidade esp = consulta.getEspecialidade();
				consultaResp = consultaLoader.novoResponse( esp );
				consultaLoader.loadResponse( consultaResp, consulta );
			}
			
			List<ExameItemResponse> examesListaResp = new ArrayList<>();
			for( ExameItem ei : exames ) {
				ExameItemResponse eiResp = exameItemLoader.novoResponse();
				exameItemLoader.loadResponse( eiResp, ei );
				
				examesListaResp.add( eiResp );
			}
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( 
					clinica, profissional, paciente,
					consultaResp, examesListaResp );
			atendimentoLoader.loadResponse( resp, atendimento );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public AtendimentoResponse get( Long id ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( id );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		
		Clinica clinica = atendimento.getClinica();
		Profissional profissional = atendimento.getProfissional();
		Paciente paciente = atendimento.getPaciente();
		
		Consulta consulta = atendimento.getConsulta();
		List<ExameItem> exames = atendimento.getExames();
		
		ConsultaResponse consultaResp = null;
		if ( atendimento.isTemConsulta() ) {
			Especialidade esp = consulta.getEspecialidade();
			consultaResp = consultaLoader.novoResponse( esp );
			consultaLoader.loadResponse( consultaResp, consulta );
		}
		
		List<ExameItemResponse> examesListaResp = new ArrayList<>();
		for( ExameItem ei : exames ) {
			ExameItemResponse eiResp = exameItemLoader.novoResponse();
			exameItemLoader.loadResponse( eiResp, ei );
			
			examesListaResp.add( eiResp );
		}
					
		AtendimentoResponse resp = atendimentoLoader.novoResponse( 
				clinica, profissional, paciente,
				consultaResp, examesListaResp );
		atendimentoLoader.loadResponse( resp, atendimento );
		
		return resp;
	}
	
	public AtendimentoRegLoadResponse getRegLoad( Long profissionalId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();				
		List<ProfissionalEspecialidadeVinculo> especialidadesVinculos = profissional.getProfissionalEspecialidadeVinculos();
		List<ProfissionalExameVinculo> examesVinculos = profissional.getProfissionalExameVinculos();
		
		List<EspecialidadeResponse> especialidadeLista = new ArrayList<>();
		for( ProfissionalEspecialidadeVinculo v : especialidadesVinculos ) {
			Especialidade esp = v.getEspecialidade();
			
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, esp );
			
			especialidadeLista.add( resp );
		}
		
		List<ProfissionalExameVinculoResponse> exameLista = new ArrayList<>();
		for( ProfissionalExameVinculo v : examesVinculos ) {
			Exame exame = v.getExame();
			
			ProfissionalExameVinculoResponse resp = profissionalExameVinculoLoader.novoResponse( exame );
			profissionalExameVinculoLoader.loadResponse( resp, v );
			
			exameLista.add( resp );
		}
		
		AtendimentoRegLoadResponse resp = atendimentoLoader.novoRegResponse( especialidadeLista, exameLista );
		atendimentoLoader.loadRegResponse( resp );
		return resp;
	}
	
	public AtendimentoRemarcarLoadResponse getRemarcarLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		
		AtendimentoRemarcarLoadResponse resp = atendimentoLoader.novoRemarcarResponse( atendimento );
		atendimentoLoader.loadRemarcarResponse( resp );
		return resp;
	}
	
	public AtendimentoPagamentoLoadResponse getPagamentoLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Consulta consulta = atendimento.getConsulta();
		List<ExameItem> exames = atendimento.getExames();
		
		return atendimentoLoader.novoPagamentoResponse( atendimento, consulta, exames );
	}
	
	public AtendimentoAlterLoadResponse getAlterLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Clinica clinica = atendimento.getClinica();
		Profissional profissional = atendimento.getProfissional();
		Paciente paciente = atendimento.getPaciente();
		
		Consulta consulta = atendimento.getConsulta();
		List<ExameItem> exames = atendimento.getExames();
		
		ConsultaResponse consultaResp = null;
		if ( atendimento.isTemConsulta() ) {
			Especialidade esp = consulta.getEspecialidade();
			consultaResp = consultaLoader.novoResponse( esp );
			consultaLoader.loadResponse( consultaResp, consulta );
		}
		
		List<ExameItemResponse> examesListaResp = new ArrayList<>();
		for( ExameItem ei : exames ) {
			ExameItemResponse eiResp = exameItemLoader.novoResponse();
			exameItemLoader.loadResponse( eiResp, ei );
			
			examesListaResp.add( eiResp );
		}
					
		AtendimentoResponse aresp = atendimentoLoader.novoResponse( 
				clinica, profissional, paciente,
				consultaResp, examesListaResp );
		
		atendimentoLoader.loadResponse( aresp, atendimento );
		
		return atendimentoLoader.novoAlterResponse( aresp );
	}
	
	public AtendimentoTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		AtendimentoTelaLoadResponse resp = atendimentoLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
		atendimentoLoader.loadTelaResponse( resp );
		return resp;
	}
	
	public NovoAtendimentoRegLoadResponse getNovoAtendimentoRegLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return atendimentoLoader.novoAtendimentoRegLoadResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public AtendimentoListaFilaTelaLoadResponse getFiltroResumidoTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		AtendimentoListaFilaTelaLoadResponse resp = atendimentoLoader.novoFilaTelaResponse( clinicasIDs2, clinicasNomes2 );
		atendimentoLoader.loadListaFilaTelaResponse( resp );
		return resp;
	}
	
	public AtendimentoIniciadaTelaLoadResponse getIniciadoTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		AtendimentoIniciadaTelaLoadResponse resp = atendimentoLoader.novoIniciadaTelaResponse( clinicasIDs2, clinicasNomes2 );
		atendimentoLoader.loadIniciadaTelaResponse( resp );
		return resp;
	}
		
	public AtendimentoAgendaLoadResponse getAtendimentoAgendaLoad( Long[] clinicasIDs ) {
		ListaResponse lresp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		AtendimentoAgendaLoadResponse resp = atendimentoLoader.novoConsultaAgendaLoadResponse( lresp.getIds(), lresp.getNomes() );
		atendimentoLoader.loadConsultaAgendaResponse( resp ); 
		return resp;
	}
		
	public List<Object[]> agrupaPorDiaDeMes( Long atendimentoId, int mes, int ano ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );			
		
		Atendimento atendimento = atendimentoOp.get();
		Long clinicaId = atendimento.getClinica().getId();
		Long profissionalId = atendimento.getProfissional().getId();
				
		return this.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );
	}
	
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano ) throws ServiceException {

		return atendimentoRepository.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );		
	}
			
	@Transactional
	public void deleta( Long logadoUID, Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent())
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
						
		Atendimento atendimento = atendimentoOp.get();
		
		if ( atendimento.isPago() ) {
			Clinica clinica = atendimento.getClinica();

			Optional<Usuario> usuarioOp = usuarioRepository.findById( logadoUID );
			if ( !usuarioOp.isPresent() )
				throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
			
			Usuario usuarioLogado = usuarioOp.get();
			
			Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, clinica );
			lanc.setDataLancamento( new Date() );
			lanc.setTipo( LancamentoTipo.DEBITO );
			lanc.setValor( atendimento.getValorPago() );
			lanc.setObservacoes( Info.PAGAMENTO_DEBITADO ); 
			
			lancamentoRepository.save( lanc );
		}
		
		atendimentoRepository.deleteById( atendimentoId ); 
	}
}
