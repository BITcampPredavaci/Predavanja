package list.linked;

/**
 * {@link ListNode} implementation. 
 * 
 * @author emir
 *
 */
public class ValueWrapper implements ListNode {
	private final Object value;
	private ValueWrapper next;
	
	public ValueWrapper(Object value) {
		this.value = value;
	}
	
	public ValueWrapper getNext() {
		return next;
	}
	
	public void setNext(ValueWrapper node) {
		next = node;
	}
	
	public Object getValue() {
		return value;
	}	
}
