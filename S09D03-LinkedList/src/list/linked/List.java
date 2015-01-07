package list.linked;

/**
 * Simple implementation of basic list methods, based on single linked element wrappers.
 * 
 * @author emir
 *
 */
public class List {
	private ValueWrapper first, last;
	
	/**
	 * Adds provided element at the end of list
	 * @param el to add
	 */
	public void add(Object el) {
		ValueWrapper wrapper = new ValueWrapper(el);
		if (last != null) {
			last.setNext(wrapper);
			last = wrapper;
		} else {
			first = last = wrapper;
		}
	}
	
	/**
	 * @return first node of this list or null if list is empty
	 */
	public ListNode firstNode() {
		return first;
	}
	
	/**
	 * Pushes provided element at the beginning of list 
	 * @param el to push
	 */
	public void push(Object el) {
		ValueWrapper wrapper = new ValueWrapper(el);
		wrapper.setNext(first);
		first = wrapper;
		// in case first element push 
		if (last == null) {
			last = first;
		}
	}
	
	/**
	 * Removes first element from the list or throw {@link ListIndexExceededException} exception if list is empty.
	 * @return removed element
	 */
	public Object pop() {
		if (first != null) {
			Object val = first.getValue();
			first = first.getNext();
			// in case of last element pop
			if (first == null) {
				last = null;
			}
			return val;
		}
		throw new ListIndexExceededException(0);
	}
	
	/**
	 * Returns element at specified index or 
	 * throws {@link ListIndexExceededException} exception if list is not big enough to reach that index. 
	 * @param index
	 * @return element
	 */
	public Object get(int index) {
		ValueWrapper el = getWrapper(index);
		if (el != null) {
			return el.getValue();
		}
		throw new ListIndexExceededException(index);
	}
	
	/**
	 * Removes element at specified index or 
	 * throws {@link ListIndexExceededException} exception if list is not big enough to reach that index. 
	 * @param index
	 * @return removed element
	 */
	public Object removeAt(int index) {
		if (index == 0) {
			return pop();
		} else {
			ValueWrapper prev = getWrapper(index - 1);
			if (prev != null) {
				ValueWrapper toRemove = prev.getNext();
				if (toRemove != null) {
					ValueWrapper next = toRemove.getNext();
					prev.setNext(next);
					// if last removed
					if (next == null) {
						last = prev;
					}
					return toRemove.getValue();
				}
			}
			throw new ListIndexExceededException(index);
		}	 
	}
	
	/**
	 * Inserts element at specified index or 
	 * throws {@link ListIndexExceededException} exception if list is not big enough to reach that index. 
	 * @param index
	 * @return element
	 */
	public void insertAt(Object el, int index) {
		if (index == 0) {
			push(el);
		} else {
			ValueWrapper wrapper = new ValueWrapper(el);
			ValueWrapper prev = getWrapper(index - 1);
			if (prev != null) {
				ValueWrapper next = prev.getNext();
				wrapper.setNext(next);
				prev.setNext(wrapper);
				// in case of inserting at the end
				if (next == null) {
					last = wrapper;
				}
			} else {
				throw new ListIndexExceededException(index);
			}
		}
	}
	
	/**
	 * Iterates list and returns value wrapper for provided index or null if index exceeds list's size
	 * @param index to return wrapper for
	 * @return wrapper for provided index
	 */
	private ValueWrapper getWrapper(int index) {
		ValueWrapper res = first;
		int cnt = 0;
		while (cnt++ < index && res != null) {
			res = res.getNext();
		}
		return res;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		ValueWrapper wrapper = first;
		while (wrapper != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}
			sb.append(wrapper.getValue());
			wrapper = wrapper.getNext();
		}
		return sb.append(']').toString();
	}
}
