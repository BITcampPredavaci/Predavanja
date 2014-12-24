package racing;

import java.awt.Color;
import java.awt.Graphics;

public class Road implements AnimatedGameArtifact {
	private int left;
	private int right;
	private int speed;
	private int height;
	
	public static int RODE_SIDE = 50;
	public static int LINE_WIDTH = 10;
	
	private int lineHeight = 50;
	private int lineSpace = 20;
	private int totalLineHeight = lineHeight + lineSpace;
	
	private int start;

	public Road(int width, int height, int speed) {
		this.height = height;
		this.speed = speed;
		this.left = RODE_SIDE - LINE_WIDTH;
		this.right = width - RODE_SIDE;
	}

	@Override
	public void animateFrame(long frameNumber) {
		start = (int) (frameNumber % totalLineHeight) * speed;
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		drawLine(graphics, left);
		drawLine(graphics, right);
	}

	private void drawLine(Graphics graphics, int position) {
		int y = start - speed * totalLineHeight;
		while (y < height + totalLineHeight) {
			graphics.drawRect(position, y, LINE_WIDTH, lineHeight);
			y += 70;
		}
		
	}
}
