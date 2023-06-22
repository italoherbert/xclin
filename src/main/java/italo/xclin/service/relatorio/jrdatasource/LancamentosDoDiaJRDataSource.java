package italo.xclin.service.relatorio.jrdatasource;

import java.util.List;

import italo.xclin.enums.tipos.LancamentoTipo;
import italo.xclin.logica.Converter;
import italo.xclin.model.Lancamento;
import italo.xclin.model.Usuario;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class LancamentosDoDiaJRDataSource implements JRDataSource {

	private List<Lancamento> lancamentos;
	private int i = -1;
	private int size = 0;
	private double total = 0;
	
	private final Converter converter;
	
	public LancamentosDoDiaJRDataSource( List<Lancamento> lancamentos, Converter converter ) {
		this.converter = converter;
		this.lancamentos = lancamentos;
		
		if ( lancamentos.isEmpty() ) {
			Usuario u = new Usuario();
			u.setUsername( "Nenhum" ); 
			
			Lancamento l = new Lancamento();
			l.setUsuario( u );
			l.setTipo( LancamentoTipo.CREDITO );
			l.setValor( 0 );
			l.setObservacoes( "" ); 
			
			this.lancamentos.add( l );
		}
		
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
		
		String field = jrField.getName();
		if ( field.equals( "usuario" ) ) {
			return usuario.getUsername();
		} else if ( field.equals( "tipo" ) ) {
			return lancamento.getTipo().label();
		} else if ( field.equals( "entrada" ) ) {
			double valor = 0;
			if ( lancamento.getTipo() == LancamentoTipo.CREDITO )
				valor = lancamento.getValor();			
			return converter.formataReal( valor );
		} else if ( field.equals( "saida" ) ) {
			double valor = 0;
			if ( lancamento.getTipo() == LancamentoTipo.DEBITO )
				valor = lancamento.getValor() ;
			return converter.formataReal( valor );
		} else if ( field.equals( "total" ) ) {			
			if ( lancamento.getTipo() == LancamentoTipo.CREDITO )
				total += lancamento.getValor();
			else if ( lancamento.getTipo() == LancamentoTipo.DEBITO ) {
				total -= lancamento.getValor();
			}
			return converter.formataReal( total );
		} else if ( field.equals( "totalValor" ) ) {
			return total;
		} else if ( field.equals( "obs" ) ) {
			return lancamento.getObservacoes();
		}
		return null;
	}
	
	
	
}
