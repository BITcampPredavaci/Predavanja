package ba.bitcamp.lectures.patterns.factory;

public class FancyTextComponent implements Component {

	private String text;

	public FancyTextComponent(String text) {
		this.text = text;
	}
	
	@Override
	public String serialize() {
		return "Uuu! I am fancy: " + text;
	}

}
