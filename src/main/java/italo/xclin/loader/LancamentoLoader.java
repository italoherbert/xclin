package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.LancamentoTipoEnumManager;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.LancamentoSaveRequest;
import italo.xclin.model.response.LancamentoResponse;
import italo.xclin.model.response.load.reg.LancamentoRegLoadResponse;
import italo.xclin.model.response.load.tela.LancamentoTelaLoadResponse;

@Component
public class LancamentoLoader {

	@Autowired
	private LancamentoTipoEnumManager lancamentoTipoEnumManager;
	
	@Autowired
	private Converter converter;
	
	public void loadBean( Lancamento l, LancamentoSaveRequest request ) throws LoaderException {
		l.setTipo( lancamentoTipoEnumManager.getEnum( request.getTipo() ) );
		l.setValor( request.getValor() );
		l.setDataLancamento( new Date() ); 	
		l.setObservacoes( request.getObservacoes() );
	}
	
	public void loadResponse( LancamentoResponse resp, Lancamento l ) {		
		resp.setId( l.getId() );
		
		resp.setTipo( l.getTipo().name() );
		resp.setTipoLabel( l.getTipo().label() );
		
		resp.setValor( l.getValor() );
		resp.setDataLancamento( converter.dataHoraToString( l.getDataLancamento() ) );
		resp.setObservacoes( l.getObservacoes() );
	}
	
	public void loadRegResponse( LancamentoRegLoadResponse resp ) {
		resp.setLancamentoTipos( lancamentoTipoEnumManager.tipoResponses() ); 
	}
	
	public Lancamento novoBean( Usuario u, Clinica c ) {
		Lancamento l = new Lancamento();
		l.setUsuario( u ); 
		l.setClinica( c ); 
		return l;
	}
	
	public LancamentoResponse novoResponse( Usuario u, Clinica c ) {
		LancamentoResponse resp = new LancamentoResponse();
		resp.setUsuarioId( u.getId() );
		resp.setUsuarioUsername( u.getUsername() ); 
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
		return resp;
	}
	
	public LancamentoTelaLoadResponse novoLancamentoTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		LancamentoTelaLoadResponse resp = new LancamentoTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public LancamentoRegLoadResponse novoLancamentoRegResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		LancamentoRegLoadResponse resp = new LancamentoRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
}
