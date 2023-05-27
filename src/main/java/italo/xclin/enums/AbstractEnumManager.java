package italo.xclin.enums;

import java.util.ArrayList;
import java.util.List;

import italo.xclin.model.response.TipoResponse;

public abstract class AbstractEnumManager<T extends Enum<T>> {  
	
	protected abstract T[] values();
	
	protected abstract String label( T e );
	
	public boolean enumValida( String tipo ) {
		if ( tipo == null )
			return false;
		
		T[] valores = this.values();
		for( int i = 0; i < valores.length; i++ )
			if ( tipo.equalsIgnoreCase( valores[ i ].name() ) )
				return true;
		return false;
	}
	
	public String getEnumString( T nome ) {
		T[] valores = this.values();
		for( int i = 0; i < valores.length; i++ )
			if ( nome == valores[ i ] )
				return valores[ i ].name();	
		
		return null;
	}
			
	public T getEnum( String nome ) {
		if ( nome == null )
			return null;
			
		T[] valores = this.values();
		for( int i = 0; i < valores.length; i++ )
			if ( nome.equalsIgnoreCase( valores[ i ].name() ) )
				return valores[ i ];		

		return null;
	}
	
	public TipoResponse tipoResponse( T tipo ) {		
		TipoResponse resp = new TipoResponse();
		if ( tipo == null ) {
			resp.setName( "" );
			resp.setLabel( "" ); 
		} else {
			resp.setName( tipo.name() );
			resp.setLabel( this.label( tipo ) );
		}
		
		return resp;
	}
	
	public List<TipoResponse> tipoResponses() {
		List<TipoResponse> lista = new ArrayList<>();
		
		T[] values = this.values();
		for( T t : values )
			lista.add( this.tipoResponse( t ) );		
			
		return lista;
	}
	
}
