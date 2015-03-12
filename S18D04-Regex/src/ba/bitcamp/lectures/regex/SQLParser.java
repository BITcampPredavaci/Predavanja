package ba.bitcamp.lectures.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demo how regex can be used to parse structured data.
 * 
 * @author emir
 *
 */
public class SQLParser {
	private static Pattern SQL_SELECT = Pattern.compile("^SELECT\\s+(.+)\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)$", Pattern.CASE_INSENSITIVE); 
	
	public static void parseSql(String sql) {
		Matcher m = SQL_SELECT.matcher(sql);
		if (m.find()) {
			System.out.println("Tabela:" + m.group(2));
			System.out.println("Kolone:" + m.group(1));
			System.out.println("Filter:" + m.group(3));
		} else {
			System.err.println("Invalid SQL");
		}
	}
	
	public static void main(String[] args) {
		parseSql("select col1 from table1 where id = 2");
		parseSql("SELECT col1 FROM table1 WHERE id = 2");
	}
}
