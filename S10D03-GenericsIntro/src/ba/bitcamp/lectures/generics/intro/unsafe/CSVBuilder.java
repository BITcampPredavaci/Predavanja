package ba.bitcamp.lectures.generics.intro.unsafe;

/**
 * Class for creating CSV line in inefficient way using string concat.
 * 
 * @author emir
 *
 */
public class CSVBuilder {
	String res;
	
	/**
	 * Appends provided string to existing CSV line.
	 * 
	 * @param str value to append to line
	 * @return this builder
	 * @throws NullPointerException in case provided object is null
	 */
	public CSVBuilder append(String str) {
		if (res != null) {
			res = res.concat(",").concat(str);
		} else {
			res = str;
		}
		return this;
	}
	
	/**
	 * Appends string representation of provided object to existing CSV line.
	 * 
	 * @param str value to append to line
	 * @return this builder
	 * @throws NullPointerException in case provided object is null
	 */
	public CSVBuilder append(Object obj) {
		return append(obj.toString());
	}
	
	/**
	 * Resets builder.
	 */
	public void clear() {
		res = null;
	}
	
	@Override
	public String toString() {
		if (res != null) {
			return res;
		}
		return "";
	}
}
