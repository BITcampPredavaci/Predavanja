package timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StopWatch extends JPanel implements ActionListener {
	private Date start;
	private int events;
	private int delay;
	
	public StopWatch(String title, int delay) {
		setPreferredSize(new Dimension(300, 100));
		setBorder(BorderFactory.createTitledBorder(title));
		

		this.delay = delay;
		start = new Date();
		Timer timer = new Timer(delay, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		events++;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		long prosloSekundi = (new Date().getTime() - start.getTime()) / 1000;
		g.drawString("Time seconds:  " + prosloSekundi, 10, 30);
		g.drawString("Event seconds: " + events*delay/1000, 10, 60);
	}
	
	public static void main(String[] args) {
		JPanel content = new JPanel();		
		content.add(new StopWatch("1 event per second", 1000));
		content.add(new StopWatch("10 events per second", 100));
		content.add(new StopWatch("100 events per second", 10));
		content.add(new StopWatch("500 events per second", 2));
		
		
		JFrame window = new JFrame("Second tracker");
		window.setSize(300, 450);
		window.setLocation(50, 50);
		window.setResizable(false);
		window.setContentPane(content);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setVisible(true);
	}

	
}
