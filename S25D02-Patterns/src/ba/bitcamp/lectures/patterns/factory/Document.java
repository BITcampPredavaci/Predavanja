package ba.bitcamp.lectures.patterns.factory;

public class Document {
	private AbstractComponentFactory factory;

	public Document(AbstractComponentFactory factory) {
		this.factory = factory;
	}
	
	public Component getDocument() {
		// use provided factory to build document
		ComplexComponent c1 = factory.getComplexComponent("test", "bla", "foo");
		Component c2 = factory.getTextComponent("this is text");
		Component c3 = factory.getTextComponent("more text");
		// but can be combined with those component that are not factory specific
		
		return new ComplexComponent(c1, c2, c3);
	}
	
	
	public static void main(String[] args) {
		// different factories will produce different result
		Document plainDocument = new Document(new PlainTextComponentFactory());
		System.out.println(plainDocument.getDocument().serialize());
		
		Document fancyDocument = new Document(new FancyTextComponentFactory());
		System.out.println(fancyDocument.getDocument().serialize());
	}
}
