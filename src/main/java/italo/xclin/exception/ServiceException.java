package italo.xclin.exception;

public class ServiceException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String mensagem, String... params) {
		super(mensagem, params);
	}

}
