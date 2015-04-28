package ba.bitcamp.lectures.patterns.factory;

public class FancyTextComponentFactory extends AbstractComponentFactory {

	@Override
	public Component getTextComponent(String text) {
		return new FancyTextComponent(text);
	}

}
