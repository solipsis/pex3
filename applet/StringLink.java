package applet;

import java.awt.Color;
import java.awt.Graphics2D;

public class StringLink extends BoundedObject {

	private String value;
	private Color textColor;
	
	
	/**
	 * Constructs a new String link. It as essentially a String that
	 * has a bounding box associated with it
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param value
	 */
	public StringLink(int x, int y, int width, int height, String value) {
		super(x, y, width, height);
		this.value = value;
	}
	
	/**
	 * Draws the string
	 * @param g2d
	 */
	public void paintComponent(Graphics2D g2d) {
		g2d.setColor(textColor);
		g2d.drawString(value, getX(), getY());		
	}
	
	
//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	/**
	 * Retrieves the string value of the String
	 * @return value of the string
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the color to draw the string in
	 * @param c
	 */
	public void setColor(Color c) {
		textColor = c;
	}

}
