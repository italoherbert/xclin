package italo.scm.exception;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String mensagem;
	protected String[] params;

	private String erroMensagem;
	
	public SistemaException( String mensagem, String...params ) {
		this.mensagem = mensagem;
		this.params = params;
				
		erroMensagem = mensagem;
		for( int i = 0; i < params.length; i++ )
			erroMensagem = erroMensagem.replaceFirst( "\\$"+(i+1), params[ i ] );		
	}

	public String getErroMensagem() {
		return erroMensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public String[] getParams() {
		return params;
	}

}
