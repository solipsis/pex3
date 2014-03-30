package applet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * simple class that holds an actor name and a picture
 * as well as bounding box info for clicking
 */
public class Actor {
	
	private String name;
	private BufferedImage image;
	private Color textColor;
	private Rectangle2D rect;
	private int x, y;

	public Actor(String name, BufferedImage image, int x, int y) {
		this.name = name;
		this.image = image;
		this.x = x;
		this.y = y;
		rect = new Rectangle2D.Double(x,y,150,150);
	}
	
	/**
	 * Paints the actor and his picture
	 */
	public void paintComponent(Graphics2D g2d) {
		g2d.setColor(textColor);
		g2d.drawString(name, x, y);
		g2d.drawImage(image, x, y+5, 125, 150, null);
		
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

	public Rectangle2D getRect() {
		return rect;
	}
	
	public void setColor(Color c) {
		textColor = c;
	}
	
}
