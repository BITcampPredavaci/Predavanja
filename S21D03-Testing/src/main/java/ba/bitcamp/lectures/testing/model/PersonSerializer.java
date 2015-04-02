package ba.bitcamp.lectures.testing.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class PersonSerializer {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
	}
	
	public static String serialize(Person person) {
		try {
			return MAPPER.writeValueAsString(person);
		} catch (IOException e) {
			throw new RuntimeException("Unexpected serialization error", e);
		}
	}
	
	public static String serialize(Collection<Person> collection) {
		try {
			return MAPPER.writeValueAsString(collection);
		} catch (IOException e) {
			throw new RuntimeException("Unexpected serialization error", e);
		}
	}
	
	public static Person deserialize(String json) {
		try {
			return MAPPER.readValue(json, Person.class);
		} catch (IOException e) {
			throw new RuntimeException("Unexpected deserialization error", e);
		}
	}
	
	public static Person deserialize(InputStream jsonStream) {
		try {
			return MAPPER.readValue(jsonStream, Person.class);
		} catch (IOException e) {
			throw new RuntimeException("Unexpected deserialization error", e);
		}
	}	
}
