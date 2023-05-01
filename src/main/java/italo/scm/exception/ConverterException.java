package italo.scm.exception;

public class ConverterException extends SistemaException {

	private static final long serialVersionUID = 1L;
	
	public ConverterException(String mensagem, String... params) {
		super(mensagem, params);
	}

	public void throwLoaderException() throws LoaderException {
		throw new LoaderException( super.mensagem, super.params );
	}
	
}
