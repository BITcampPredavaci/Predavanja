package racing;

import java.awt.Color;
import java.awt.Graphics;

public class AnimatedVehicle extends Vehicle implements AnimatedGameArtifact {

	private int speed;
	private int roadHeight;
	private int minX;
	private int maxX;

	public AnimatedVehicle(int minX, int maxX, int roadHeight, int speed) {
		super(0, 0);
		this.minX = minX;
		this.maxX = maxX;
		this.roadHeight = roadHeight;
		setSpeed(speed);
		resetPosition();
	}
	
	public void resetPosition() {
		setPosition((int) (minX + Math.random() * (maxX - minX)), (int)(-HEIGHT * Math.random() * 10));
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed / 2;
	}

	@Override
	public void animateFrame(long frameNumber) {
		// move down or reset position if passed
		if (y < roadHeight) {
			y += speed;
		} else {
			resetPosition();
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		// draw car only if in visible area
		if (y > -HEIGHT && y < roadHeight) {
			drawCar(graphics, Color.GREEN);
		}
	}

}
