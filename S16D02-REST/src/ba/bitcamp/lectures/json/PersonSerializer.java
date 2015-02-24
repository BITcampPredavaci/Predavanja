package ba.bitcamp.lectures.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class PersonSerializer {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
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
	
	public static void main(String[] args) {
		Person el = new Person(1, "test", new int[] {2, 3});
		String json = serialize(el);
		System.out.println(json);
		
		Person dEl = deserialize(json);
		System.out.println(dEl.getId());
		System.out.println(dEl.getName());
		System.out.println(Arrays.toString(dEl.getRelated()));
	}
}
