package italo.xclin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.LancamentoLoader;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.LancamentoFiltroRequest;
import italo.xclin.model.request.save.LancamentoSaveRequest;
import italo.xclin.model.response.LancamentoResponse;
import italo.xclin.model.response.load.reg.LancamentoRegLoadResponse;
import italo.xclin.model.response.load.tela.LancamentoTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.repository.UsuarioRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private LancamentoLoader lancamentoLoader;
	
	@Autowired
	private Converter converter;
	
	public void registra( Long logadoUID, Long clinicaId, LancamentoSaveRequest request ) throws ServiceException {
		Optional<Usuario> usuarioLogadoOp = usuarioRepository.findById( logadoUID );
		if ( !usuarioLogadoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
				
		Usuario usuarioLogado = usuarioLogadoOp.get();
		Clinica clinica = clinicaOp.get();
		
		Lancamento l = lancamentoLoader.novoBean( usuarioLogado, clinica );
		lancamentoLoader.loadBean( l, request );
		
		lancamentoRepository.save( l );		
	}
	
	public LancamentoResponse get( Long lancamentoId ) throws ServiceException {
		Optional<Lancamento> lancamentoOp = lancamentoRepository.findById( lancamentoId );
		if ( !lancamentoOp.isPresent() )
			throw new ServiceException( Erro.LANCAMENTO_NAO_ENCONTRADO );
		
		Lancamento l = lancamentoOp.get();
		Usuario u = l.getUsuario();
		Clinica c = l.getClinica();
		
		LancamentoResponse resp = lancamentoLoader.novoResponse( u, c );
		lancamentoLoader.loadResponse( resp, l );
		
		return resp;
	}
	
	public List<LancamentoResponse> filtra( Long clinicaId, LancamentoFiltroRequest request ) throws ServiceException {
		Date dataIni = converter.stringToDataNEx( request.getDataInicio() );
		Date dataFim = converter.stringToDataNEx( request.getDataFim() );
		boolean incluirUsername = request.isIncluirUsername();
		String filtroUsername = "%"+request.getFiltroUsername()+"%"; 
		
		List<Lancamento> lancamentos = 
				lancamentoRepository.filtra( clinicaId, dataIni, dataFim, incluirUsername, filtroUsername );
		
		List<LancamentoResponse> lista = new ArrayList<>();
		for( Lancamento l : lancamentos ) {
			Usuario u = l.getUsuario();
			Clinica c = l.getClinica();
			
			LancamentoResponse resp = lancamentoLoader.novoResponse( u, c );
			lancamentoLoader.loadResponse( resp, l );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public LancamentoTelaLoadResponse getLancamentoTelaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return lancamentoLoader.novoLancamentoTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public LancamentoRegLoadResponse getLancamentoRegLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		LancamentoRegLoadResponse resp = 
				lancamentoLoader.novoLancamentoRegResponse( clinicasIDs2, clinicasNomes2 );
		
		lancamentoLoader.loadRegResponse( resp );
		return resp;
	}
	
	public void deleta( Long lancamentoId ) throws ServiceException {
		boolean existe = lancamentoRepository.existsById( lancamentoId );
		if ( !existe )
			throw new ServiceException( Erro.LANCAMENTO_NAO_ENCONTRADO );
		
		lancamentoRepository.deleteById( lancamentoId );
		
	}
	
}
