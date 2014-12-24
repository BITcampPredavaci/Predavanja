package racing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RacingGame extends JPanel implements ActionListener {
	
	private static final int MOVE_STEP = 5;
	private static final int MIN_SPEED = 10;
	private static final int MAX_SPEED = 100;
	private static final int DEFAULT_SPEED = 20;
	private static final int SPEED_CHANGE = 5;

	private long frameNumber;

	private GameArtifact[] gameArtifacts;
	private AnimatedGameArtifact[] animatedGameArtifacts;
	private AnimatedVehicle[] animatedVehicles;

	private int minX, maxX;
	private Vehicle myVehicle;
	private int speed;
	private Timer timer;

	/**
	 * Constructs new RacingGame for provided road width and height with starting game speed
	 * @param roadWidth
	 * @param roadHeight
	 * @param speed
	 */
	public RacingGame(int roadWidth, int roadHeight, int speed) {
		this.speed = speed;
		this.minX = Road.RODE_SIDE;
		this.maxX = roadWidth - Road.RODE_SIDE;

		this.myVehicle = new Vehicle(400, 500);
		Road road = new Road(roadWidth, roadHeight, speed);
		AnimatedVehicle otherVehicle = new AnimatedVehicle(minX, maxX, roadHeight, speed);

		gameArtifacts = new GameArtifact[] { myVehicle, road, otherVehicle };
		animatedGameArtifacts = new AnimatedGameArtifact[] { road, otherVehicle };
		animatedVehicles = new AnimatedVehicle[] {otherVehicle};

		// timer created with this RacingGame as ActionListener
		timer = new Timer(1000 / 10, this);

		// added key listener as anonymous class that extends KeyAdapter (KeyAdapter implements KeyListener)
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					myVehicle.x = Math.max(minX, myVehicle.x - MOVE_STEP);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					myVehicle.x = Math.min(maxX, myVehicle.x + MOVE_STEP);
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					setSpeed(SPEED_CHANGE);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					setSpeed(-SPEED_CHANGE);
				}
			}
		});

		// added mouse listener as anonymous class that extends MouseAdapter
		// used to obtain focus on mouse click
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				requestFocus();
			}
		});

		// added focus listener as anonymous class that implements FocusListener
		// animation is started only if RacingGame is focused - at that time you can only move vehicle
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent evt) {
				timer.start();
				repaint();
			}

			public void focusLost(FocusEvent evt) {
				timer.stop();
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw every game artifact on any repaint
		for (GameArtifact ga : gameArtifacts) {
			ga.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// increment frame number and inform every animated artifact that frame changed
		frameNumber++;
		for (AnimatedGameArtifact aga : animatedGameArtifacts) {
			aga.animateFrame(frameNumber);
		}
		
		// check if  vehicles colided
		for (AnimatedVehicle av : animatedVehicles) {
			if (myVehicle.colade(av)) {
				myVehicle.crash();
				av.resetPosition();
			}
		}
		repaint();
	}

	/**
	 * Calculates new speed and informs every animated artifact that speed has changed.
	 * @param speedChange speed increment
	 */
	public void setSpeed(int speedChange) {
		speed = Math.min(Math.max(MIN_SPEED, speed + speedChange), MAX_SPEED);
		for (AnimatedGameArtifact aga : animatedGameArtifacts) {
			aga.setSpeed(speed);
		}
	}

	public static void main(String[] args) {
		RacingGame racingGame = new RacingGame(800, 600, DEFAULT_SPEED);

		JFrame window = new JFrame("Racing Game");
		window.setSize(800, 600);
		window.setLocation(50, 50);
		window.setResizable(false);
		window.setContentPane(racingGame);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
