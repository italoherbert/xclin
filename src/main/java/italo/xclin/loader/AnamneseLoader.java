package italo.xclin.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.CicatrizacaoEnumManager;
import italo.xclin.enums.PressaoArterialEnumManager;
import italo.xclin.enums.SangramentoEnumManager;
import italo.xclin.enums.SangramentoNaGengivaEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Anamnese;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.save.AnamneseSaveRequest;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;

@Component
public class AnamneseLoader {

	@Autowired
	private PressaoArterialEnumManager pressaoArterialEnumManager;
	
	@Autowired
	private CicatrizacaoEnumManager cicatrizacaoEnumManager;
	
	@Autowired
	private SangramentoEnumManager sangramentoEnumManager;
	
	@Autowired
	private SangramentoNaGengivaEnumManager sangramentoNaGentivaEnumManager;
	
	@Autowired
	private Converter converter;
	
	public void loadBean( Anamnese a, AnamneseSaveRequest request ) throws LoaderException {
		a.setTomaMedicamentos( request.isTomaMedicamentos() );
		a.setQuaisMedicamentos( request.getQuaisMedicamentos() );
		a.setTemAlergias( request.isTemAlergias() );
		a.setQuaisAlergias( request.getQuaisAlergias() );
		a.setTemOuTeveProblemasDoCoracao( request.isTemOuTeveProblemasDoCoracao() );
		a.setQuaisProblemasDoCoracao( request.getQuaisProblemasDoCoracao() );
		a.setTemFaltaDeAr( request.isTemFaltaDeAr() );
		a.setTemDiabetes( request.isTemDiabetes() ); 
		a.setJaFezCirurgia( request.isJaFezCirurgia() );
		a.setEhGestante( request.isEhGestante() );
		a.setSemanasDeGestacao( request.getSemanasDeGestacao() ); 
		a.setProblemasDeSaude( request.getProblemasDeSaude() );
		a.setQueixaPrincipal( request.getQueixaPrincipal() );
		a.setJaTeveReacaoComAnestesiaDental( request.isJaTeveReacaoComAnestesiaDental() );
		a.setQuaisReacoesTeveComAnestesiaDental( request.getQuaisReacoesTeveComAnestesiaDental() );
		a.setSenteDoresNosDentesOuGengiva( request.isSenteDoresNosDentesOuGengiva() );		
		a.setSenteGostoRuimNaBoca( request.isSenteGostoRuimNaBoca() );
		a.setQuantasVezesEscovaOsDentes( request.getQuantasVezesEscovaOsDentes() );
		a.setUsaFioDental( request.isUsaFioDental() );
		a.setSenteDoresNoMaxilarOuOuvido( request.isSenteDoresNoMaxilarOuOuvido() );
		a.setRangeOsDentes( request.isRangeOsDentes() );
		a.setTeveFeridaNaFaceOuNosLabios( request.isTeveFeridaNaFaceOuNosLabios() );
		a.setFuma( request.isFuma() );
		a.setQuantidadeQueFuma( request.getQuantidadeQueFuma() ); 
		
		if ( request.getCicatrizacao() != null )
			a.setCicatrizacao( cicatrizacaoEnumManager.getEnum( request.getCicatrizacao() ) );
		if ( request.getPressaoArterial() != null )
			a.setPressaoArterial( pressaoArterialEnumManager.getEnum( request.getPressaoArterial() ) );
		if ( request.getSangramentoQuandoSeCorta() != null )
			a.setSangramentoQuandoSeCorta( sangramentoEnumManager.getEnum( request.getSangramentoQuandoSeCorta() ) );
		if ( request.getSangramentoNaGengiva() != null )
			a.setSangramentoNaGengiva( sangramentoNaGentivaEnumManager.getEnum( request.getSangramentoNaGengiva() ) );

		try {
			a.setDataUltimoTratamentoDentario( converter.stringToData( request.getDataUltimoTratamentoDentario() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		}				
	}
	
	public void loadResponse( AnamneseResponse resp, Anamnese a ) throws LoaderException {		
		resp.setTomaMedicamentos( a.isTomaMedicamentos() );
		resp.setQuaisMedicamentos( a.getQuaisMedicamentos() );
		resp.setTemAlergias( a.isTemAlergias() );
		resp.setQuaisAlergias( a.getQuaisAlergias() );
		resp.setTemOuTeveProblemasDoCoracao( a.isTemOuTeveProblemasDoCoracao() );
		resp.setQuaisProblemasDoCoracao( a.getQuaisProblemasDoCoracao() );
		resp.setTemFaltaDeAr( a.isTemFaltaDeAr() );
		resp.setTemDiabetes( a.isTemDiabetes() ); 
		resp.setJaFezCirurgia( a.isJaFezCirurgia() );
		resp.setEhGestante( a.isEhGestante() );
		resp.setSemanasDeGestacao( a.getSemanasDeGestacao() ); 
		resp.setProblemasDeSaude( a.getProblemasDeSaude() );
		resp.setQueixaPrincipal( a.getQueixaPrincipal() );
		resp.setJaTeveReacaoComAnestesiaDental( a.isJaTeveReacaoComAnestesiaDental() );
		resp.setQuaisReacoesTeveComAnestesiaDental( a.getQuaisReacoesTeveComAnestesiaDental() ); 
		resp.setSenteDoresNosDentesOuGengiva( a.isSenteDoresNosDentesOuGengiva() );		
		resp.setSenteGostoRuimNaBoca( a.isSenteGostoRuimNaBoca() );
		resp.setQuantasVezesEscovaOsDentes( a.getQuantasVezesEscovaOsDentes() );
		resp.setUsaFioDental( a.isUsaFioDental() );
		resp.setSenteDoresNoMaxilarOuOuvido( a.isSenteDoresNoMaxilarOuOuvido() );
		resp.setRangeOsDentes( a.isRangeOsDentes() );
		resp.setTeveFeridaNaFaceOuNosLabios( a.isTeveFeridaNaFaceOuNosLabios() );
		resp.setFuma( a.isFuma() );
		resp.setQuantidadeQueFuma( a.getQuantidadeQueFuma() ); 
		
		if ( a.getCicatrizacao() != null ) {
			resp.setCicatrizacao( a.getCicatrizacao().name() );
			resp.setCicatrizacaoLabel( a.getCicatrizacao().label() );
		}
		if ( a.getPressaoArterial() != null ) {
			resp.setPressaoArterial( a.getPressaoArterial().name() );
			resp.setPressaoArterialLabel( a.getPressaoArterial().label() );
		}
		if ( a.getSangramentoQuandoSeCorta() != null ) {			
			resp.setSangramentoQuandoSeCorta( a.getSangramentoQuandoSeCorta().name() );
			resp.setSangramentoQuandoSeCortaLabel( a.getSangramentoQuandoSeCorta().label() ); 
		}
		if ( a.getSangramentoNaGengiva() != null ) {
			resp.setSangramentoNaGengiva( a.getSangramentoNaGengiva().name() );
			resp.setSangramentoNaGentivaLabel( a.getSangramentoNaGengiva().label() ); 

		}
		resp.setDataUltimoTratamentoDentario( converter.dataToString( a.getDataUltimoTratamentoDentario() ) );		
	}		
	
	public void loadEditResponse( AnamneseEditLoadResponse resp, String pacienteNome ) {
		resp.setPressaoArterialTipos( pressaoArterialEnumManager.tipoResponses() );
		resp.setCicatrizacaoTipos( cicatrizacaoEnumManager.tipoResponses() );
		resp.setSangramentoTipos( sangramentoEnumManager.tipoResponses() );
		resp.setSangramentoNaGengivaTipos( sangramentoNaGentivaEnumManager.tipoResponses() ); 
		resp.setPacienteNome( pacienteNome );
	}
	
	public Anamnese novoBean( Paciente p ) {
		Anamnese a = new Anamnese();
		a.setPaciente( p ); 
		return a;
	}
	
	public AnamneseResponse novoResponse( Paciente p ) {
		AnamneseResponse resp = new AnamneseResponse();
		resp.setPacienteId( p.getId() );
		resp.setPacienteNome( p.getNome() ); 
		return resp;
	}
		
	public AnamneseEditLoadResponse novoEditResponse( AnamneseResponse aresp ) {
		AnamneseEditLoadResponse resp = new AnamneseEditLoadResponse();
		resp.setAnamnese( aresp );
		resp.setAnamnesePreenchida( true ); 
		return resp;
	}
	
	public AnamneseEditLoadResponse novoEditResponse() {
		AnamneseEditLoadResponse resp = new AnamneseEditLoadResponse();
		resp.setAnamnesePreenchida( false );
		return resp;
	}
	
}
