package italo.scm.exception;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	private String erroMensagem;
	
	public SistemaException( String mensagem, String...params ) {
		erroMensagem = mensagem;
		for( int i = 0; i < params.length; i++ )
			erroMensagem = erroMensagem.replaceFirst( "$"+(i+1), params[ i ] );
	}

	public String getErroMensagem() {
		return erroMensagem;
	}

}
