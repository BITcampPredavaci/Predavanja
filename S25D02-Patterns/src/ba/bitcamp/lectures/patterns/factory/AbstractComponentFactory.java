package ba.bitcamp.lectures.patterns.factory;

public abstract class AbstractComponentFactory {
	public abstract Component getTextComponent(String text);
	
	public ComplexComponent getComplexComponent(String...texts) {
		Component[] components = new Component[texts.length];
		for (int i=0; i<texts.length; i++) {
			components[i] = getTextComponent(texts[i]);
		} 
		return new ComplexComponent(components);
	} 
}
