package circle.decorators;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	JPanel panel;
	
	public MainFrame() {
		super("Circles!");
		
		panel = new MainPanel();
		
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
	}
}
