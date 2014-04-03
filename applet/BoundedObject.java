package applet;

import java.awt.geom.Rectangle2D;

/**
 * 
 * Object with a bounding box so that it can easily be used with listeners
 *
 */
public class BoundedObject {
	private int x;
	private int y;
	//private int width;
	//private int height;
	private Rectangle2D rect = new Rectangle2D.Double();
		
	public BoundedObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		//this.width = width;
		//this.height = height;
		rect = new Rectangle2D.Double(x, y, width, height); 
	}
//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	public Rectangle2D getRect() {
		return rect;
	}
	
	public void setRect(double x, double y, int width, int height) {
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
