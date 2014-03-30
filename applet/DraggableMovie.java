package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class DraggableMovie  {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String title;
	private Rectangle2D rect;
	
	public DraggableMovie(int x, int y, String title ) {
		this.x = x;
		this.y = y;
		this.title = title;
		width = 20;
		height = 20;
		rect = new Rectangle2D.Double(x,y,20,20);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, 20, 20);
		g2d.setColor(Color.BLUE);
		g2d.drawString(title, x, y);
		//g.setColor(Color.RED);
		//g.fillRect(x, y, 20, 20);
	}

	public Rectangle2D getRect() {
		return rect;
	}
	
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
