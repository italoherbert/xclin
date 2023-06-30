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
import italo.xclin.loader.EspecialidadeLoader;
import italo.xclin.loader.LancamentoLoader;
import italo.xclin.loader.PacienteAnexoLoader;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.Profissional;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.AtendimentoFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaFiltroRequest;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoRemarcarSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;
import italo.xclin.model.response.AtendimentoIniciadoResponse;
import italo.xclin.model.response.AtendimentoObservacoesResponse;
import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoAgendaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.AtendimentoRepository;
import italo.xclin.repository.EspecialidadeRepository;
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
	private Converter converter;

	@Autowired
	private AtendimentoStatusEnumManager atendimentoStatusEnumManager;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	public void registra( 
			Long clinicaId, 
			Long profissionalId,
			Long especialidadeId, 
			Long pacienteId, 
			AtendimentoSaveRequest request ) throws ServiceException {
		
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
		
		Atendimento atendimento = atendimentoLoader.novoBean( profissional, especialidade, paciente, clinica );
		atendimentoLoader.loadBean( atendimento, request );
		
		atendimentoRepository.save( atendimento );
	}
	
	public void altera( Long atendimentoId, AtendimentoAlterSaveRequest request ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		AtendimentoStatus status = atendimentoStatusEnumManager.getEnum( request.getStatus() );		
		if ( status == AtendimentoStatus.INICIADO )
			throw new ServiceException( Erro.NAO_PODE_INICIAR_ATENDIMENTO );
		
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
	public void setaPagamento( Long logadoUID, Long atendimentoId, boolean paga ) throws ServiceException {				
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
				
		Atendimento atendimento = atendimentoOp.get();
		atendimento.setPaga( paga );			
		
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = usuarioLogadoOp.get();
		
		atendimentoRepository.save( atendimento );
					
		Clinica c = atendimento.getClinica();
		Lancamento lanc = lancamentoLoader.novoBean( usuarioLogado, c );
		lanc.setDataLancamento( new Date() );
		if ( paga ) {			
			lanc.setTipo( LancamentoTipo.CREDITO );
			lanc.setObservacoes( Info.PAGAMENTO_CREDITADO );
		} else {
			lanc.setTipo( LancamentoTipo.DEBITO ); 
			lanc.setObservacoes( Info.PAGAMENTO_DEBITADO ); 
		}
		lanc.setValor( atendimento.getValor() ); 
		
		lancamentoRepository.save( lanc );
	}
		
	public void cancelaConsulta( Long atendimentoId ) throws ServiceException {
		this.alteraStatus( atendimentoId, AtendimentoStatus.CANCELADO ); 
	}
	
	public void finalizaConsulta( Long atendimentoId ) throws ServiceException {
		this.alteraStatus( atendimentoId, AtendimentoStatus.FINALIZADO );
	}
	
	public void alteraStatus( Long atendimentoId, AtendimentoStatus status ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		atendimento.setStatus( status );
		
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
	
	public AtendimentoIniciadoResponse getIniciada( 
			Long logadoUID, Long clinicaId, String turnoStr, int histObsPageSize ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Turno turno = turnoEnumManager.getEnum( turnoStr );		
		
		Optional<Atendimento> atendimentoOp = atendimentoRepository.getIniciada( clinicaId, profissionalId, turno );
				
		int quantPacientesNaFila = atendimentoRepository.contaFila( clinicaId, profissionalId, turno );

		if ( atendimentoOp.isPresent() ) {						
			Atendimento atendimento = atendimentoOp.get();
			Clinica c = atendimento.getClinica();
			Profissional pr = atendimento.getProfissional();
			Paciente pa = atendimento.getPaciente();
			Especialidade e = atendimento.getEspecialidade();
						
			AtendimentoResponse cresp = atendimentoLoader.novoResponse( c, pr, pa, e );
			atendimentoLoader.loadResponse( cresp, atendimento );
			
			Long pacienteId = pa.getId();
			
			List<AtendimentoObservacoesResponse> historicoObservacoes = this.getUltimasObservacoes( 
					clinicaId, profissionalId, pacienteId, histObsPageSize );			
			
			List<PacienteAnexo> anexos = pa.getAnexos();
			
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
				request.isIncluirPagas(), 
				request.isIncluirRetornos(),
				dataIni, dataFim );
		
		List<AtendimentoResponse> lista = new ArrayList<>();
		for( Atendimento atendimento : atendimentos ) {
			Clinica c = atendimento.getClinica();
			Profissional pr = atendimento.getProfissional();
			Paciente pa = atendimento.getPaciente();
			Especialidade e = atendimento.getEspecialidade();
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( c, pr, pa, e );
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
			Clinica c = atendimento.getClinica();
			Profissional pr = atendimento.getProfissional();
			Paciente pa = atendimento.getPaciente();
			Especialidade e = atendimento.getEspecialidade();
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( c, pr, pa, e );
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
			Clinica c = atendimento.getClinica();
			Profissional pr = atendimento.getProfissional();
			Paciente pa = atendimento.getPaciente();
			Especialidade e = atendimento.getEspecialidade();
						
			AtendimentoResponse resp = atendimentoLoader.novoResponse( c, pr, pa, e );
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
		Clinica c = atendimento.getClinica();
		Profissional pr = atendimento.getProfissional();
		Paciente pa = atendimento.getPaciente();
		Especialidade e = atendimento.getEspecialidade();
					
		AtendimentoResponse resp = atendimentoLoader.novoResponse( c, pr, pa, e );
		atendimentoLoader.loadResponse( resp, atendimento );
		
		return resp;
	}
	
	public AtendimentoRegLoadResponse getRegLoad( Long profissionalId ) {
		List<Especialidade> especialidades = especialidadeRepository.listaPorProfissional( profissionalId );
		
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		
		AtendimentoRegLoadResponse resp = atendimentoLoader.novoRegResponse( lista );
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
	
	public AtendimentoAlterLoadResponse getAlterLoad( Long atendimentoId ) throws ServiceException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Clinica clinica = atendimento.getClinica();
		Profissional profissional = atendimento.getProfissional();
		Paciente paciente = atendimento.getPaciente();
		Especialidade especialidade = atendimento.getEspecialidade();
		
		AtendimentoResponse cresp = atendimentoLoader.novoResponse( clinica, profissional, paciente, especialidade );
		atendimentoLoader.loadResponse( cresp, atendimento );
		
		AtendimentoAlterLoadResponse resp = atendimentoLoader.novoAlterResponse( cresp );
		atendimentoLoader.loadAlterResponse( resp );
		return resp;
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
	
	public NovoAtendimentoLoadResponse getNovoAtendimentoLoad( Long[] clinicasIDs ) {
		ListaResponse resp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		return atendimentoLoader.novoNovaConsultaLoadResponse( resp.getIds(), resp.getNomes() ); 
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
			
	public void deleta( Long atendimentoId ) throws ServiceException {
		boolean existe = atendimentoRepository.existsById( atendimentoId );
		if ( !existe )
			throw new ServiceException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		atendimentoRepository.deleteById( atendimentoId ); 
	}
}
