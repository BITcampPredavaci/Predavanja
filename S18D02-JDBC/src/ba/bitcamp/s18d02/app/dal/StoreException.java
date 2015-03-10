package ba.bitcamp.s18d02.app.dal;

public class StoreException extends Exception {
	private static final long serialVersionUID = -6276729831490633614L;

	public StoreException() {
	}
	
	public StoreException(String message) {
		 super(message);
	}
	
	public StoreException(Exception inner) {
		 super(inner);
	}
}