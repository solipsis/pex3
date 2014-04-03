package applet;

import java.awt.geom.Rectangle2D;

/**
 * TODO: remove x and y attributes. All can be grabbed through rect
 */

/**
 * 
 * Object with a bounding box so that it can easily be used with listeners
 *
 */
public class BoundedObject {
	private int x;
	private int y;
	private Rectangle2D rect = new Rectangle2D.Double();
		
	/**
	 * Constructs a new BoundedObjects. These objectts have a bounding
	 * rectangle so they can easily be used with gui listeners
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BoundedObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		rect = new Rectangle2D.Double(x, y, width, height); 
	}
//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	/**
	 * Accessor method to get the bounding box of the object
	 * @return
	 */
	public Rectangle2D getRect() {
		return rect;
	}
	
	/**
	 * Sets the bounding box of the object
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setRect(double x, double y, int width, int height) {
		rect.setRect(x, y, width, height);
	}
	
	/**
	 * Accessor that returns the x coordinate of the object
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x value of object
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Accessor that returns the y coordinate of the object
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the object
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
