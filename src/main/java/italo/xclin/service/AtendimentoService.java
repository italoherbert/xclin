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
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AtendimentoLoader;
import italo.xclin.loader.ConsultaLoader;
import italo.xclin.loader.EspecialidadeLoader;
import italo.xclin.loader.ExameItemLoader;
import italo.xclin.loader.LancamentoLoader;
import italo.xclin.loader.OrcamentoLoader;
import italo.xclin.loader.PacienteAnexoLoader;
import italo.xclin.loader.ProcedimentoItemLoader;
import italo.xclin.loader.ProfissionalExameVinculoLoader;
import italo.xclin.loader.ProfissionalProcedimentoVinculoLoader;
import italo.xclin.logica.Converter;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Clinica;
import italo.xclin.model.Consulta;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Exame;
import italo.xclin.model.ExameItem;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Orcamento;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.Procedimento;
import italo.xclin.model.ProcedimentoItem;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalEspecialidadeVinculo;
import italo.xclin.model.ProfissionalExameVinculo;
import italo.xclin.model.ProfissionalProcedimentoVinculo;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.AtendimentoFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaFiltroRequest;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoRemarcarSaveRequest;
import italo.xclin.model.request.save.AtendimentoRetornoSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.request.save.ExameItemSaveRequest;
import italo.xclin.model.request.save.OrcamentoPagamentoSaveRequest;
import italo.xclin.model.request.save.OrcamentoSaveRequest;
import italo.xclin.model.request.save.ProcedimentoItemSaveRequest;
import italo.xclin.model.response.AtendimentoIniciadoResponse;
import italo.xclin.model.response.AtendimentoObservacoesResponse;
import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.ExameItemResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.OrcamentoResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.ProcedimentoItemResponse;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.ProfissionalProcedimentoVinculoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRetornoLoadResponse;
import italo.xclin.model.response.load.edit.OrcamentoPagamentoLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoAgendaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoTelaLoadResponse;
import italo.xclin.repository.AtendimentoRepository;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.ConsultaRepository;
import italo.xclin.repository.EspecialidadeRepository;
import italo.xclin.repository.ExameItemRepository;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.repository.OrcamentoRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.repository.ProcedimentoItemRepository;
import italo.xclin.repository.ProcedimentoRepository;
import italo.xclin.repository.ProfissionalRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.ClinicaSharedService;
import jakarta.transaction.Transactional;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
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
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ExameItemRepository exameItemRepository;
	
	@Autowired
	private ProcedimentoItemRepository procedimentoItemRepository;
		
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private AtendimentoLoader atendimentoLoader;
	
	@Autowired
	private OrcamentoLoader orcamentoLoader;
	
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
	private ProfissionalProcedimentoVinculoLoader profissionalProcedimentoVinculoLoader;
	
	@Autowired
	private ExameItemLoader exameItemLoader;
	
	@Autowired
	private ProcedimentoItemLoader procedimentoItemLoader;
	
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
		
		OrcamentoSaveRequest orcamentoReq = request.getOrcamento();
		
		Consulta consulta = null;
		if ( orcamentoReq.isTemConsulta() ) {
			ConsultaSaveRequest consultaRequest = orcamentoReq.getConsulta();
			Long especialidadeId = consultaRequest.getEspecialidadeId();
			
			Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
			if ( !especialidadeOp.isPresent() )
				throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );

			Especialidade especialidade = especialidadeOp.get();
									
			consulta = consultaLoader.novoBean( especialidade );
			consultaLoader.loadBean( consulta, consultaRequest ); 
		}
		
		List<ExameItem> exames = new ArrayList<>();
		for( ExameItemSaveRequest reqExame : orcamentoReq.getExames() ) {
			Long exameId = reqExame.getExameId();
			
			Optional<Exame> exameOp = exameRepository.findById( exameId );
			if ( !exameOp.isPresent() )
				throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
			
			Exame exame = exameOp.get();
			ExameItem exameItem = exameItemLoader.novoBean( exame );
			exameItemLoader.loadBean( exameItem, reqExame );			
			
			exames.add( exameItem );
		}
		
		List<ProcedimentoItem> procedimentos = new ArrayList<>();
		for( ProcedimentoItemSaveRequest reqProc : orcamentoReq.getProcedimentos() ) {
			Long procedimentoId = reqProc.getProcedimentoId();
			
			Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
			if ( !procedimentoOp.isPresent() )
				throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
			
			Procedimento procedimento = procedimentoOp.get();
			ProcedimentoItem procItem = procedimentoItemLoader.novoBean( procedimento );
			procedimentoItemLoader.loadBean( procItem, reqProc ); 
			
			procedimentos.add( procItem );
		}
						
		Orcamento orcamento = orcamentoLoader.novoBean( consulta, exames, procedimentos );
		orcamentoLoader.loadBean( orcamento, orcamentoReq ); 
		
		Atendimento atendimento = atendimentoLoader.novoBean( 
				profissional, 
				paciente, 
				clinica, 
				orcamento );
		
		atendimentoLoader.loadBean( atendimento, request );
		atendimento.setStatus( AtendimentoStatus.REGISTRADO ); 
		
		atendimentoRepository.save( atendimento );
				
		if ( request.getOrcamento().getValorPago() > 0 ) {
			Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, clinica );		
			lanc.setDataLancamento( new Date() );
			lanc.setTipo( LancamentoTipo.CREDITO );
			lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );
			
			lanc.setValor( request.getOrcamento().getValorPago() );

			lancamentoRepository.save( lanc );		
		}		
	}
	
	public void registraRetorno( Long atendimentoId, AtendimentoRetornoSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Clinica clinica = atendimento.getClinica();
		Profissional profissional = atendimento.getProfissional();
		Paciente paciente = atendimento.getPaciente();
		
		Orcamento orcamento = atendimento.getOrcamento();
		
		Atendimento novoAtendimento = atendimentoLoader.novoBean( profissional, paciente, clinica, orcamento );
		atendimentoLoader.loadBean( novoAtendimento, request );
		
		atendimentoRepository.save( novoAtendimento );
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
	public void efetuaPagamento( Long logadoUID, Long atendimentoId, OrcamentoPagamentoSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
						
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		Atendimento atendimento = atendimentoOp.get();
		Orcamento orcamento = atendimento.getOrcamento();
		
		orcamentoLoader.loadBean( orcamento, request ); 
		
		orcamentoRepository.save( orcamento );
		
		Clinica c = atendimento.getClinica();
		
		Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, c );
		lanc.setDataLancamento( new Date() );
		lanc.setTipo( LancamentoTipo.CREDITO );
		lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );		
		lanc.setValor( request.getValorPago() ); 
		
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
		Orcamento orcamento = atendimento.getOrcamento();
		
		double valorPago = orcamento.getValorPago();
				
		orcamento.setPago( false );
		orcamento.setValorPago( 0 );
		
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
		Orcamento orcamento = atendimento.getOrcamento();
		if ( orcamento.isPago() )
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
			Paciente paciente = atendimento.getPaciente();
			
			AtendimentoResponse atResp = this.atendimentoResponse( atendimento );		
			
			Long pacienteId = paciente.getId();
			
			List<Atendimento> atendimentosUltObss = atendimentoRepository.getUltimasObservacoes(
					clinicaId, profissionalId, pacienteId, PageRequest.of( 0, histObsPageSize ) );
						
			List<AtendimentoObservacoesResponse> observacoesLista = new ArrayList<>();
			for( Atendimento at : atendimentosUltObss ) {
				AtendimentoObservacoesResponse resp = atendimentoLoader.novoObservacoesResponse( at );
				observacoesLista.add( resp );
			}	
			
			List<PacienteAnexo> anexos = paciente.getAnexos();
			
			List<PacienteAnexoResponse> respAnexos = new ArrayList<>();
			for( PacienteAnexo a : anexos ) {
				PacienteAnexoResponse aresp = pacienteAnexoLoader.novoResponse();
				pacienteAnexoLoader.loadResponse( aresp, a );
				
				respAnexos.add( aresp );
			}
			
			return atendimentoLoader.novoIniciadoResponse( 
					atResp, observacoesLista, respAnexos, quantPacientesNaFila );
		}
		
		return atendimentoLoader.novoNenhumaIniciadaResponse( quantPacientesNaFila );		
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
			AtendimentoResponse resp = this.atendimentoResponse( atendimento );			
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
			AtendimentoResponse resp = this.atendimentoResponse( atendimento );
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
			AtendimentoResponse resp = this.atendimentoResponse( atendimento );
			lista.add( resp );
		}
		return lista;
	}
	
	public AtendimentoResponse get( Long id ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( id );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		
		AtendimentoResponse resp = this.atendimentoResponse( atendimento );		
		return resp;
	}
	
	public AtendimentoRegLoadResponse getRegLoad( Long profissionalId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();				
		List<ProfissionalEspecialidadeVinculo> especialidadesVinculos = profissional.getProfissionalEspecialidadeVinculos();
		List<ProfissionalExameVinculo> examesVinculos = profissional.getProfissionalExameVinculos();
		List<ProfissionalProcedimentoVinculo> procedimentosVinculos = profissional.getProfissionalProcedimentoVinculos();
		
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
		
		List<ProfissionalProcedimentoVinculoResponse> procedimentosLista = new ArrayList<>();
		for( ProfissionalProcedimentoVinculo v : procedimentosVinculos ) {
			Procedimento procedimento = v.getProcedimento();
			
			ProfissionalProcedimentoVinculoResponse resp = profissionalProcedimentoVinculoLoader.novoResponse( procedimento );
			profissionalProcedimentoVinculoLoader.loadResponse( resp, v );
			
			procedimentosLista.add( resp );
		}
		
		AtendimentoRegLoadResponse resp = atendimentoLoader.novoRegResponse( especialidadeLista, exameLista, procedimentosLista );
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
	
	public AtendimentoRetornoLoadResponse retornoLoad() throws ServiceException {
		AtendimentoRetornoLoadResponse resp = atendimentoLoader.novoRetornoResponse();
		atendimentoLoader.loadRetornoResponse( resp );
		return resp;
	}
	
	public OrcamentoPagamentoLoadResponse getPagamentoLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Orcamento orcamento = atendimento.getOrcamento();

		Consulta consulta = orcamento.getConsulta();
		List<ExameItem> exames = orcamento.getExames();
		List<ProcedimentoItem> procedimentos = orcamento.getProcedimentos();
		
		return orcamentoLoader.novoPagamentoResponse( orcamento, consulta, exames, procedimentos );
	}
	
	public AtendimentoAlterLoadResponse getAlterLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		
		AtendimentoResponse resp = this.atendimentoResponse( atendimento );		
		return atendimentoLoader.novoAlterResponse( resp );
	}
	
	public AtendimentoTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
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
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return atendimentoLoader.novoAtendimentoRegLoadResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public AtendimentoListaFilaTelaLoadResponse getFiltroResumidoTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
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
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
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
	
	public void alterConsultaConcluida( Long consultaId, boolean concluida ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		consulta.setConcluida( concluida );
		
		consultaRepository.save( consulta );
	}
	
	public void alterExameItemConcluido( Long exameItemId, boolean concluido ) throws ServiceException {
		Optional<ExameItem> exameItemOp = exameItemRepository.findById( exameItemId );
		if ( !exameItemOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		ExameItem exameItem = exameItemOp.get();
		exameItem.setConcluido( concluido );
		
		exameItemRepository.save( exameItem );
	}
	
	public void alterProcedimentoItemConcluido( Long procedimentoItemId, boolean concluido ) throws ServiceException {
		Optional<ProcedimentoItem> procedimentoItemOp = procedimentoItemRepository.findById( procedimentoItemId );
		if ( !procedimentoItemOp.isPresent() )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		ProcedimentoItem procedimentoItem = procedimentoItemOp.get();
		procedimentoItem.setConcluido( concluido );
		
		procedimentoItemRepository.save( procedimentoItem );
	}
			
	@Transactional
	public void deleta( Long logadoUID, Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent())
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
						
		Atendimento atendimento = atendimentoOp.get();
		Orcamento orcamento = atendimento.getOrcamento();
				
		if ( orcamento.isPago() ) {
			Clinica clinica = atendimento.getClinica();

			Optional<Usuario> usuarioOp = usuarioRepository.findById( logadoUID );
			if ( !usuarioOp.isPresent() )
				throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
			
			Usuario usuarioLogado = usuarioOp.get();
			
			Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, clinica );
			lanc.setDataLancamento( new Date() );
			lanc.setTipo( LancamentoTipo.DEBITO );
			lanc.setValor( orcamento.getValorPago() );
			lanc.setObservacoes( Info.PAGAMENTO_DEBITADO ); 
			
			lancamentoRepository.save( lanc );
		}
		
		Long orcamentoId = orcamento.getId();
		boolean orcamentoVinculado = orcamentoRepository.vinculadoComAtendimento( orcamentoId );
		if ( !orcamentoVinculado )
			orcamentoRepository.deleteById( orcamentoId );
		
		atendimentoRepository.deleteById( atendimentoId ); 
	}
	
	private AtendimentoResponse atendimentoResponse( Atendimento atendimento ) throws ServiceException {
		Clinica clinica = atendimento.getClinica();
		Profissional profissional2 = atendimento.getProfissional();
		Paciente paciente = atendimento.getPaciente();
		
		Orcamento orcamento = atendimento.getOrcamento();
		
		Consulta consulta = orcamento.getConsulta();
		List<ExameItem> exames = orcamento.getExames();
		List<ProcedimentoItem> procedimentos = orcamento.getProcedimentos();
		
		ConsultaResponse consultaResp = null;
		if ( orcamento.isTemConsulta() ) {
			Especialidade esp = consulta.getConsultaEspecialidadeVinculo().getEspecialidade();
			consultaResp = consultaLoader.novoResponse( esp );
			consultaLoader.loadResponse( consultaResp, consulta );
		}
		
		List<ExameItemResponse> examesListaResp = new ArrayList<>();
		for( ExameItem ei : exames ) {
			ExameItemResponse eiResp = exameItemLoader.novoResponse();
			exameItemLoader.loadResponse( eiResp, ei );
			
			examesListaResp.add( eiResp );
		}
		
		List<ProcedimentoItemResponse> procedimentosListaResp = new ArrayList<>();
		for( ProcedimentoItem pi : procedimentos ) {
			ProcedimentoItemResponse piResp = procedimentoItemLoader.novoResponse();
			procedimentoItemLoader.loadResponse( piResp, pi );
			
			procedimentosListaResp.add( piResp );
		}
					
		OrcamentoResponse orcamentoResp = orcamentoLoader.novoResponse( consultaResp, examesListaResp, procedimentosListaResp );
		orcamentoLoader.loadResponse( orcamentoResp, orcamento );
		
		AtendimentoResponse resp = atendimentoLoader.novoResponse( 
				clinica,
				profissional2, 
				paciente, 
				orcamentoResp );
		
		atendimentoLoader.loadResponse( resp, atendimento );
		
		return resp;
	}
	
}
