package ba.bitcamp.lectures.patterns.factory;

public class PlainTextComponentFactory extends AbstractComponentFactory {

	@Override
	public Component getTextComponent(String text) {
		return new PlainTextComponent(text);
	}

}
