package list.linked;

/**
 * Runtime exception thrown if index based list operations failed.
 * 
 * @author emir
 *
 */
public class ListIndexExceededException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ListIndexExceededException(int index) {
		super("Invalid index " + index);
	}
}
