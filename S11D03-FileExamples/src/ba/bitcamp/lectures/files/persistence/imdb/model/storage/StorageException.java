package ba.bitcamp.lectures.files.persistence.imdb.model.storage;

/**
 * Runtime exception that can occur while calling storage methods.
 * 
 * @author emir
 *
 */
public class StorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StorageException(String msg) {
		super(msg);
	}

}
