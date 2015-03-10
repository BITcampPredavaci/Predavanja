package ba.bitcamp.s18d02.app.business;

public class PurchaseLoadException extends Exception {
	private static final long serialVersionUID = 1101645897479145949L;

	public PurchaseLoadException() {
	}
	
	public PurchaseLoadException(String message) {
		 super(message);
	}
	
	public PurchaseLoadException(Exception inner) {
		 super(inner);
	}
}
