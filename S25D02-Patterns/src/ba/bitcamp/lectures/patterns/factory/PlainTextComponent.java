package ba.bitcamp.lectures.patterns.factory;

public class PlainTextComponent implements Component {

	private String text;

	public PlainTextComponent(String text) {
		this.text = text;
	}
	
	@Override
	public String serialize() {
		return text;
	}

}
