package italo.xclin.logica;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	private String ACENTOS = "ÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙÃÕáéíóúâêîôûàèìòùãõ";
	private String SEM_ACENTOS = "AEIOUAEIOUAEIOUAOaeiouaeiouaeiouao";
	
	public String removeAcentos( String str ) {		
		StringBuilder nova = new StringBuilder();
		
		int len = str.length();
		for( int i = 0; i < len; i++ ) {
			char ch = str.charAt( i );
			
			int len2 = ACENTOS.length();
									
			char novaCH = '\0';
			for( int j = 0; novaCH == '\0' && j < len2; j++ ) {
				if ( ch == ACENTOS.charAt( j ) ) {
					novaCH = SEM_ACENTOS.charAt( j );
				}
			}
			
			if ( novaCH == '\0' )
				novaCH = ch;
			
			nova.append( novaCH );
		}
		
		return nova.toString();
	}
	
	/*
	public static void main(String[] args) {
		StringUtil util = new StringUtil();
		
		System.out.println( util.removeAcentos( "Ítalo" ) );
		System.out.println( util.removeAcentos( "áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙÂÊÎÔÛâêîôûãõÃÕ" ) );		
	}
	*/
	
}
