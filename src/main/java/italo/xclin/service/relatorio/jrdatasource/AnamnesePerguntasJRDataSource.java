package italo.xclin.service.relatorio.jrdatasource;

import java.util.List;

import italo.xclin.model.AnamnesePergunta;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AnamnesePerguntasJRDataSource implements JRDataSource {

	private List<AnamnesePergunta> perguntas;
	private int i = -1;
	private int size = 0;
	
	public AnamnesePerguntasJRDataSource( List<AnamnesePergunta> perguntas ) {
		this.perguntas = perguntas;
		this.size = perguntas.size();
	}

	@Override
	public boolean next() throws JRException {
		return ++i < size;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		String field = jrField.getName();
		
		AnamnesePergunta pergunta = perguntas.get( i );
		if ( field.equals( "pergunta" ) ) {
			return pergunta.getPergunta();
		} else if ( field.equals( "resposta" ) ) {
			String resposta = "";
			switch( pergunta.getTipo() ) {
				case STRING:
					resposta = pergunta.getRespostaString();
					break;
				case BOOLEAN:
					resposta = ( pergunta.isRespostaBoolean() ? "Sim" : "Não" );
					break;
				case ENUM:
					resposta = pergunta.getRespostaEnum();
					break;
			}					
			return ( resposta == null ? "" : resposta );
		}
		return null;
	}
	
}
