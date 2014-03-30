package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DraggableMovie  {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String title;
	private Rectangle2D rect;
	
	/**
	 * Constructs a new DraggableMovie.
	 * also constructs the bounding rectangle used
	 * for click detection
	 * 
	 * @param x
	 * @param y
	 * @param title
	 */
	public DraggableMovie(int x, int y, String title ) {
		this.x = x;
		this.y = y;
		this.title = title;
		width = 20;
		height = 20;
		rect = new Rectangle2D.Double(x,y,20,20);
	}
	
	
	/**
	 * Paints the movie and its title
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, 20, 20);
		g2d.setColor(Color.BLUE);
		g2d.drawString(title, x, y);
	}

	public Rectangle2D getRect() {
		return rect;
	}
	
//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	public void setRect(double x, double y) {
		rect.setRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
