package italo.xclin.relatorio.jrdatasource;

import italo.xclin.model.Anamnese;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AnamneseJRDataSource implements JRDataSource {

	private String pacienteNome;
	private Anamnese anamnese;
	
	private int i = -1;
	private int size = 1;
	
	public AnamneseJRDataSource( Anamnese anamnese, String pacienteNome ) {
		this.anamnese = anamnese;
		this.pacienteNome = pacienteNome;
	}
	
	@Override
	public boolean next() throws JRException {
		return ++i < size;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		String jrfname = jrField.getName();
		if ( jrfname.equals( "pacienteNome" ) ) {
			return pacienteNome;
		} else if ( jrfname.equals( "tomaMedicamentos" ) ) {
			return anamnese.isTomaMedicamentos() ? "Sim" : "Não";
		} else if ( jrfname.equals( "quaisMedicamentos" ) ) {
			return anamnese.getQuaisMedicamentos();
		} else if ( jrfname.equals( "temAlergias" ) ) {
			return anamnese.isTemAlergias() ? "Sim" : "Não";
		} else if ( jrfname.equals( "quaisAlergias" ) ) {
			return anamnese.getQuaisAlergias();
		} else if ( jrfname.equals( "pressaoArterial" ) ) {
			if ( anamnese.getPressaoArterial() != null )
				return anamnese.getPressaoArterial().label();
			return "";
		} else if ( jrfname.equals( "temOuTeveProblemasDoCoracao" ) ) {
			return anamnese.isTemOuTeveProblemasDoCoracao() ? "Sim" : "Não";
		} else if ( jrfname.equals( "quaisProblemasDoCoracao" ) ) {
			return anamnese.getQuaisProblemasDoCoracao();
		} else if ( jrfname.equals( "temDiabetes" ) ) {
			return anamnese.isTemDiabetes() ? "Sim" : "Não";
		} else if ( jrfname.equals( "temFaltaDeAr" ) ) {
			return anamnese.isTemFaltaDeAr() ? "Sim" : "Não";
		} else if ( jrfname.equals( "sangramentoQuandoSeCorta" ) ) {
			if ( anamnese.getSangramentoQuandoSeCorta() != null )
				return anamnese.getSangramentoQuandoSeCorta().label();
			return "";
		} else if ( jrfname.equals( "cicatrizacao" ) ) {
			if ( anamnese.getCicatrizacao() != null )
				return anamnese.getCicatrizacao().label();
			return "";
		} else if ( jrfname.equals( "jaFezCirurgia" ) ) {
			return anamnese.isJaFezCirurgia() ? "Sim" : "Não";
		} else if ( jrfname.equals( "ehGestante" ) ) {
			return anamnese.isEhGestante() ? "Sim" : "Não";
		} else if ( jrfname.equals( "semanasDeGestacao" ) ) {
			return anamnese.getSemanasDeGestacao();
		} else if ( jrfname.equals( "problemasDeSaude" ) ) {
			return anamnese.getProblemasDeSaude();
		} else if ( jrfname.equals( "queixaPrincipal" ) ) {
			return anamnese.getQueixaPrincipal();
		}
		return null;
	}

}
