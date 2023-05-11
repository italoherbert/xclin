package italo.scm.exception;

public class ClinicaAuthorizationException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public ClinicaAuthorizationException(String mensagem, String... params) {
		super(mensagem, params);
	}

}
