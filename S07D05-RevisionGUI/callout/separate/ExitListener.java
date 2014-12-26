package callout.separate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener koji prekida rad aplikacije kada je okinut.
 * 
 * @author damir
 *
 */
public class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// prekida rad aplikacije. 0 signalizira operativnom sistemu da je
		// program završio rad bez greške
		System.exit(0);
	}

}
