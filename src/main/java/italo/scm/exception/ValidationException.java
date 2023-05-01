package italo.scm.exception;

public class ValidationException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String mensagem, String... params) {
		super(mensagem, params);
	}

}
