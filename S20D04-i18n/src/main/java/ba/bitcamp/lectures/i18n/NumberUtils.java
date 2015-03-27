package ba.bitcamp.lectures.i18n;

import java.util.LinkedList;

/**
 * Utility class with method that demonstrates how number to string can be implemented.
 * 
 * NOTE Code is not best possible but more for training purposes.
 * 
 * @author emir
 *
 */
public class NumberUtils {
	public static void main(String[] args) {
		System.out.println(numToString(1234567.89, '.', ','));
		System.out.println(numToString(1234567.89, ',', '.'));
		System.out.println(numToString(67.0, ',', '.'));
		System.out.println(numToString(67, ',', '.'));
		System.out.println(numToString(67.039, ',', '.'));
	}
	
	public static String numToString(double num, char decimalSeparator, char groupSeparator) {
		int[] groups = getGroups(num);
		String fract = getFraction(num);
		
		StringBuilder sb = new StringBuilder();
		sb.append(groups[0]);
		for (int i=1; i<groups.length; i++) {
			sb.append(groupSeparator).append(groups[i]);
		}
		if (!fract.isEmpty() && !fract.equals("0")) {
			sb.append(decimalSeparator).append(fract);
		}
		
		return sb.toString();
	}

	private static int[] getGroups(double d) {
		LinkedList<Integer> groups = new LinkedList<Integer>();
		int num = (int)d;
		do {
			int group = num % 1000;
			groups.add(group);
			num = num / 1000;
		} while (num > 0);
		
		int[] groupsArr = new int[groups.size()];
		int idx = groups.size() - 1;
		for (Integer group : groups) {
			groupsArr[idx--] = group;
		}
		return groupsArr;
	}

	private static String getFraction(double d) {
		String num = String.valueOf(d);
		return num.substring(num.indexOf('.') + 1);
	}
	
}
