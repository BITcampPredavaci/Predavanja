package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Program koji svake sekunde ispisuje poruku "Prošla sekunda".
 * 
 * @author damir
 *
 */
public class TimerTest {

	public static void main(String[] args) {
		// 1000ms = 1s
		Timer t = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Prošla sekunda");
			}
		});
		
		// Moramo pokrenuti timer nakon što ga inicijaliziramo
		t.start();
	}

}
