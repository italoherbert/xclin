package italo.xclin.service.autorizador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.AutorizacaoException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.AnamneseModeloPergunta;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Clinica;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Exame;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.Procedimento;
import italo.xclin.model.Profissional;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.repository.AnamneseModeloPerguntaRepository;
import italo.xclin.repository.AtendimentoRepository;
import italo.xclin.repository.ConsultaRepository;
import italo.xclin.repository.EspecialidadeRepository;
import italo.xclin.repository.ExameItemRepository;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.repository.PacienteAnexoRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.repository.ProcedimentoItemRepository;
import italo.xclin.repository.ProcedimentoRepository;
import italo.xclin.repository.ProfissionalRepository;

@Component
public class Autorizador {

	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
		
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private AnamneseModeloPerguntaRepository anamneseModeloPerguntaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PacienteAnexoRepository pacienteAnexoRepository;
		
	@Autowired
	private ExameRepository exameRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ExameItemRepository exameItemRepository;
	
	@Autowired
	private ProcedimentoItemRepository procedimentoItemRepository;
	
	public void autorizaSeConsultaDeClinica( String authorizationHeader, Long consultaId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		boolean consultaDeClinica = consultaRepository.consultaDeClinica( consultaId, clinicasIDs );
		if ( !consultaDeClinica )
			throw new AutorizacaoException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaSeExameItemDeClinica( String authorizationHeader, Long exameItemId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		boolean exameItemDeClinica = exameItemRepository.exameItemDeClinica( exameItemId, clinicasIDs );
		if ( !exameItemDeClinica )
			throw new AutorizacaoException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaSeProcedimentoItemDeClinica( String authorizationHeader, Long procItemId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		boolean procItemDeClinica = procedimentoItemRepository.procedimentoItemDeClinica( procItemId, clinicasIDs );
		if ( !procItemDeClinica )
			throw new AutorizacaoException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaSeEspecialidadeDeClinica( String authorizationHeader, Long especialidadeId ) throws AutorizacaoException {
		Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
		if ( !especialidadeOp.isPresent() )
			throw new AutorizacaoException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade e = especialidadeOp.get();
		Long clinicaId = e.getClinica().getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId ); 
	}
	
	public void autorizaSeExameDeClinica( String authorizationHeader, Long exameId ) throws AutorizacaoException {
		Optional<Exame> exameOp = exameRepository.findById( exameId );
		if ( !exameOp.isPresent() )
			throw new AutorizacaoException( Erro.EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		Long clinicaId = exame.getClinica().getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId ); 
	}
	
	public void autorizaSeProcedimentoDeClinica( String authorizationHeader, Long procedimentoId ) throws AutorizacaoException {
		Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
		if ( !procedimentoOp.isPresent() )
			throw new AutorizacaoException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		Procedimento procedimento = procedimentoOp.get();
		Long clinicaId = procedimento.getClinica().getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId ); 
	}
		
	public void autorizaSeAnexoDeClinica( String authorizationHeader, Long pacienteAnexoId ) throws AutorizacaoException {
		Optional<PacienteAnexo> pacienteAnexoOp = pacienteAnexoRepository.findById( pacienteAnexoId );
		if ( !pacienteAnexoOp.isPresent() )
			throw new AutorizacaoException( Erro.PACIENTE_ANEXO_NAO_ENCONTRADO );
		
		PacienteAnexo anexo = pacienteAnexoOp.get();
		Long pacienteId = anexo.getPaciente().getId();
		
		this.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
	}
	
	public void autorizaSeAnamneseModeloDeProfissionalLogado( String authorizationHeader, Long anamneseModeloId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new AutorizacaoException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();		
		List<AnamneseModelo> modelos = p.getAnamneseModelos();
			
		for( AnamneseModelo am : modelos )
			if ( am.getId() == anamneseModeloId )
				return;
		
		throw new AutorizacaoException( Erro.ANAMNESE_MODELO_DE_OUTRO_PROFISSIONAL );
	}
		
	public void autorizaSeAnamneseModeloPerguntaDeProfissionalLogado(
			 String authorizationHeader, Long anamneseModeloPerguntaId ) throws AutorizacaoException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new AutorizacaoException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		Optional<AnamneseModeloPergunta> perguntaOp = anamneseModeloPerguntaRepository.findById( anamneseModeloPerguntaId );
		if ( !perguntaOp.isPresent() )
			throw new AutorizacaoException( Erro.ANAMNESE_MODELO_PERGUNTA_NAO_ENCONTRADA );
		
		AnamneseModeloPergunta pergunta = perguntaOp.get();
		Profissional p2 = pergunta.getAnamneseModelo().getProfissional();
		
		if ( p.getId() != p2.getId() )
			throw new AutorizacaoException( Erro.ANAMNESE_MODELO_PERGUNTA_DE_OUTRO_PROFISSIONAL );
	}
	
	public void autorizaSeLancamentoDeClinica( String authorizationHeader, Long lancamentoId ) throws AutorizacaoException {
		Optional<Lancamento> lancamentoOp = lancamentoRepository.findById( lancamentoId );
		if ( !lancamentoOp.isPresent() )
			throw new AutorizacaoException( Erro.LANCAMENTO_NAO_ENCONTRADO );
		
		Lancamento l = lancamentoOp.get();
		Clinica c = l.getClinica();
		
		Long clinicaId = c.getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId );
	}
		
	public void autorizaSePacienteDeClinica( String authorizationHeader, Long pacienteId ) throws AutorizacaoException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new AutorizacaoException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		Clinica c = p.getClinica();
		
		Long clinicaId = c.getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId );
	}
			
	public void autorizaSeProfissionalDeClinica( String authorizationHeader, Long clinicaId, Long profissionalId ) throws AutorizacaoException {				
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new AutorizacaoException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Profissional profissional = profissionalOp.get();
		Usuario usuario = profissional.getUsuario();
		
		List<UsuarioClinicaVinculo> vinculos = usuario.getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo v : vinculos ) {
			Clinica c = v.getClinica();
			if ( c.getId() == clinicaId )
				return;
		}
		throw new AutorizacaoException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaSeProfissionalUsuario( String authorizationHeader, Long profissionalId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( profissionalOp.isPresent() ) {
			Profissional p = profissionalOp.get();
			if( p.getId() != profissionalId )
				throw new AutorizacaoException( Erro.PROFISSIONAL_ACESSO_NAO_AUTORIZADO );
			
			return;
		}
			
		throw new AutorizacaoException( Erro.PROFISSIONAL_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaPorAtendimentoEClinica( String authorizationHeader, Long atendimentoId ) throws AutorizacaoException {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById( atendimentoId );
		if ( !atendimentoOp.isPresent() )
			throw new AutorizacaoException( Erro.ATENDIMENTO_NAO_ENCONTRADO );
		
		Atendimento atendimento = atendimentoOp.get();
		Long clinicaId = atendimento.getClinica().getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId ); 
	}
	
	public void autorizaPorClinica( String authorizationHeader, Long clinicaId ) throws AutorizacaoException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		for( Long cid : clinicasIDs )
			if ( clinicaId == cid )
				return;
				
		throw new AutorizacaoException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
			
}
