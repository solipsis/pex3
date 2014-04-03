package applet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * simple class that holds an actor name and a picture
 * as well as bounding box info for clicking
 */
public class Actor extends BoundedObject{
	
	private String name;
	private BufferedImage image;
	private Color textColor;

	//private Rectangle2D rect;
	//private int x, y;

	/**
	 * This constructor creates a new actor with a name and image
	 * as well as location info used for drawing and the bounding box
	 * 
	 * @param name
	 * @param image
	 * @param x
	 * @param y
	 */
	public Actor(String name, BufferedImage image, int x, int y) {
		super(x,y,125,150);
		this.name = name;
		this.image = image;
		
		//make this take width and height TODO
		//setRect(x, y, 125, 150);
	}
	
	/**
	 * Paints the actor and his picture
	 */
	public void paintComponent(Graphics2D g2d) {
		g2d.setColor(textColor);
		g2d.drawString(name, getX(), getY());
		g2d.drawImage(image, getX(), getY()+5, 125, 150, null);
		
	}

//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setColor(Color c) {
		textColor = c;
	}
	

	
}
