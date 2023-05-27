package italo.xclin.exception;

public class AutorizadorException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public AutorizadorException(String mensagem, String... params) {
		super(mensagem, params);
	}

}
