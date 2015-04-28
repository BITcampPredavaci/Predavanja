package ba.bitcamp.lectures.patterns.factory;

public class ComplexComponent implements Component {

	private Component[] components;

	public ComplexComponent(Component...components) {
		this.components = components;
	}
	
	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		for (Component c : components) {
			sb.append(c.serialize()).append('\n');
		}
		return sb.toString();
	}

}
