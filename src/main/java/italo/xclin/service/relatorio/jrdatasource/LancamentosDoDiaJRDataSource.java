package italo.xclin.service.relatorio.jrdatasource;

import java.util.List;

import italo.xclin.enums.tipos.LancamentoTipo;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Usuario;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class LancamentosDoDiaJRDataSource implements JRDataSource {

	private List<Lancamento> lancamentos;
	private int i = -1;
	private int size = 0;
	
	public LancamentosDoDiaJRDataSource( List<Lancamento> lancamentos ) {
		this.lancamentos = lancamentos;
		this.size = lancamentos.size();
	}

	@Override
	public boolean next() throws JRException {
		return ++i < size;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {		
		Lancamento lancamento = lancamentos.get( i );
		Usuario usuario = lancamento.getUsuario();
		
		double total = 0;
		String field = jrField.getName();
		if ( field.equals( "usuario" ) ) {
			return usuario.getUsername();
		} else if ( field.equals( "tipo" ) ) {
			return lancamento.getTipo().label();
		} else if ( field.equals( "entrada" ) ) {
			if ( lancamento.getTipo() == LancamentoTipo.CREDITO )
				return lancamento.getValor();			
			return 0D;
		} else if ( field.equals( "saida" ) ) {
			if ( lancamento.getTipo() == LancamentoTipo.DEBITO )
				return lancamento.getValor();
			return 0D;
		} else if ( field.equals( "total" ) ) {
			if ( lancamento.getTipo() == LancamentoTipo.CREDITO )
				total += lancamento.getValor();
			else if ( lancamento.getTipo() == LancamentoTipo.DEBITO ) {
				total -= lancamento.getValor();
			}
			return total;
		}		
		return null;
	}
	
	
	
}
