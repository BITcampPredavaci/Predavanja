package ba.bitcamp.lectures.files.persistence.imdb.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Simplified movie class used only to demonstrate different logic for different types
 * 
 * @author emir
 *
 */
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String ESCAPED_COMMA = "#" + String.valueOf((int)',') + ";";
	private static final String ESCAPED_PIPE = "#" + String.valueOf((int)'|') + ";";
	
	private String name;
	private int year;
	private Collection<Actor> actors;

	public Movie(String name, int year, Collection<Actor> actors) {
		this.name = name;
		this.year = year;
		this.actors = actors;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<Actor> getActors() {
		return actors;
	}
	
	@Override
	public String toString() {
		return String.format("{name:%s, year:%d, actors:%s}", name, year, actors);
	}
	
	/**
	 * Writes movie as CSV line to provided writer
	 * 
	 * @param writer to write CSV line to
	 * @throws IOException in case of writer exception
	 */
	public void writeCSVLine(Writer writer) throws IOException {
		writer.append(escapeComma(getName())).append(',').append(String.valueOf(getYear())).append(',');
		if (actors != null) {
			for (Actor actor : getActors()) {
				writer.append(escapePipe(actor.getFirstName())).append('|').append(escapePipe(actor.getLastName()));
			}
		}
		writer.write('\n');
	}
	
	private String escapeComma(String value) {
		return value.replaceAll(",", ESCAPED_COMMA);
	}
	
	private String escapePipe(String value) {
		return value.replaceAll(",", ESCAPED_PIPE);
	} 
	
	/**
	 * Parses CSV line movieName,movieYear,[actor[|actor]*]*, where actor is actorFirstName|actorLastName.
	 * 
	 * File name should have escaped comma and actor name should have escaped pipe
	 * 
	 * @param csvLine to parse
	 * @return parsed movie or null in case of parsing error
	 */
	public static Movie parseMovie(String csvLine) {
		int first = csvLine.indexOf(',');
		if (first > -1) {
			String name = csvLine.substring(0, first);
			first++;
			int second = csvLine.indexOf(',', first);
			if (second < 0) {
				second = csvLine.length();
			}
			int year = Integer.parseInt(csvLine.substring(first, second).trim());
			Collection<Actor> actors = null;
			second++;
			if (second < csvLine.length()) {
				String actorsLine = csvLine.substring(second);
				actors = parseActors(actorsLine);
			}
			return new Movie(name.replaceAll(ESCAPED_COMMA, ","), year, actors);
		}
		return null;
	}

	private static Collection<Actor> parseActors(String pipedActors) {
		String[] actorVals = pipedActors.split("\\|");
		LinkedList<Actor> actors = new LinkedList<Actor>();
		for (int i=0; i + 1<actorVals.length; i+=2) {
			actors.add(new Actor(actorVals[i], actorVals[i+1]));
		}
		return actors;
	}
}
