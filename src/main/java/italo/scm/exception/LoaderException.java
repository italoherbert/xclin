package italo.scm.exception;

public class LoaderException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public LoaderException(String mensagem, String... params) {
		super(mensagem, params);
	}
	
	public void throwServiceException() throws ServiceException {
		throw new ServiceException( super.mensagem, super.params );
	}
	
}
