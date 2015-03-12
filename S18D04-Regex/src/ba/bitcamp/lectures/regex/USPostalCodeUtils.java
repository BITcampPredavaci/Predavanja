package ba.bitcamp.lectures.regex;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstrates regex validation and extraction on US postal codes.
 *  
 * @author emir
 *
 */
public class USPostalCodeUtils {
	private static final String US_ZIP_CODE_REGEX = "(\\d{5})( ?\\d{4})?";
	
	private static final Pattern US_ZIP_CODE_PATTERN = Pattern.compile(US_ZIP_CODE_REGEX); 
	
	public static void main(String[] args) {		
		validateUncompiled("60606");
		validateCompiled("60606");
		
		validateCompiled("606061234");
		validateCompiled("60606 1234");
		
		// invalid codes
		validateCompiled("6060");
		validateCompiled("60606 123");
		validateCompiled("6060 1234");
		
		// extraction
		extractPostalCodes("This is valid 60606 and 12345 4567");
	}
	
	/**
	 * Validates zip code using static matches method.
	 * @param zip to validate
	 * @return true if code is valid
	 */
	public static boolean validateUncompiled(String zip) {
		boolean valid = Pattern.matches(US_ZIP_CODE_REGEX, zip);
		System.out.println(zip + ": " + valid);
		
		return valid;
	}
	
	/**
	 * Validates zip code using compiled pattern.
	 * @param zip to validate
	 * @return true if code is valid
	 */
	public static boolean validateCompiled(String zip) {
		Matcher matcher = US_ZIP_CODE_PATTERN.matcher(zip);
		boolean valid = matcher.matches();
		System.out.println(zip + ": " + valid);
		
		return valid;
	}
	
	/**
	 * Extracts zip codes from provided text.
	 * @param text to extract from
	 * @return collection of {@link PostalCodeMatch}
	 */
	public static Collection<PostalCodeMatch> extractPostalCodes(String text) {
		Matcher matcher = US_ZIP_CODE_PATTERN.matcher(text);
		System.out.println("Extraction postal codes from: " + text);
		LinkedList<PostalCodeMatch> postalCodes = new LinkedList<PostalCodeMatch>();
		while(matcher.find()) {
			String matchedCode = matcher.group();
			String code = matcher.group(1);
			String plusFour = matcher.group(2);
			int start = matcher.start();
			int end = matcher.end();
			PostalCodeMatch postal = new PostalCodeMatch(matchedCode, code, plusFour, start, end);
			System.out.println("\t" + postal);
			postalCodes.add(postal);
		}
		
		return postalCodes;
	}
	
	public static class PostalCodeMatch {
		public String matchedCode;
		public String code;
		public String plusFour;
		public int start;
		public int end;

		public PostalCodeMatch(String matchedCode, String code, String plusFour, int start, int end) {
			this.matchedCode = matchedCode;
			this.code = code;
			this.plusFour = plusFour;
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			return String.format("{matchedCode:\"%s\", code:%s, plusFour:%s, start:%s, end:%s}", matchedCode, code, plusFour, start, end);
		}
	}
}
