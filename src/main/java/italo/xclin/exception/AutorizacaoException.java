package italo.xclin.exception;

public class AutorizacaoException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public AutorizacaoException(String mensagem, String... params) {
		super(mensagem, params);
	}

}
