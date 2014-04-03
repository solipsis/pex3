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
	}
	
	/**
	 * Paints the actor name and his picture
	 */
	public void paintComponent(Graphics2D g2d) {
		g2d.setColor(textColor);
		g2d.drawString(name, getX(), getY());
		g2d.drawImage(image, getX(), getY()+5, 125, 150, null);
		
	}

//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	
	/**
	 * Accessor method for the actor name
	 * @return the name of the actor
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accessor method for the actor's image
	 * @return the image of the actor
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * sets the color the actors name should be drawn as
	 * @param color to draw the name in
	 */
	public void setColor(Color c) {
		textColor = c;
	}
	

	
}
