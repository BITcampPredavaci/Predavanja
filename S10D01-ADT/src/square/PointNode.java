package square;

/**
 * Element linkane liste tačaka
 * 
 * @author damir
 *
 */
public class PointNode {
	Point value;
	PointNode next;
	
	public PointNode(Point point) {
		value = point;
	}
	
	Point getValue() {
		return value;
	}
	
	PointNode getNext() {
		return next;
	}
	
	void setNext(PointNode next) {
		this.next = next;
	}
}
