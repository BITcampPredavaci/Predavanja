package ba.bitcamp.lectures.files.persistence.imdb.service;

/**
 * Runtime exception that can occur while exeuting service methods.
 * 
 * @author emir
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
