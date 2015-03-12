package ba.bitcamp.lectures.regex;

import java.util.Arrays;

/**
 * Demo class for regex related String functions.
 * 
 * @author emir
 *
 */
public class StringUtilis {
	
	public static void main(String[] args) {
		swapPlaces("abc xyz");
		swapPlaces("abc xyz bla ");
		swapPlaces("test");
		
		splitCSV("foo,bla, test     ,  test2,  ,,");
	}

	/**
	 * Swap places of string parts before and after first space.
	 * 
	 * @param toSwap
	 * @return swapped string
	 */
	public static String swapPlaces(String toSwap) {
		String swapped = toSwap.replaceAll("(.*?) (.*)", "$2 $1");
		System.out.println(toSwap + " -> " + swapped);
		return swapped;
	}
	
	/**
	 * Split csv input ignoring spaces.
	 * @param csv to split
	 * @return values
	 */
	public static String[] splitCSV(String csv) {
		String[] vals = csv.split("\\s*,\\s*", -1); // -1 is to include empty values at end
		System.out.println(csv + " -> " + Arrays.toString(vals));
		return vals;
	}
}
