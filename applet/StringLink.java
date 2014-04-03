package applet;

import java.awt.Color;
import java.awt.Graphics2D;

public class StringLink extends BoundedObject {

	private String value;
	private Color textColor;
	
	public StringLink(int x, int y, int width, int height, String value) {
		super(x, y, width, height);
		this.value = value;
	}
	
	public void paintComponent(Graphics2D g2d) {
		g2d.setColor(textColor);
		g2d.drawString(value, getX(), getY());		
	}
	
	
//-----------------------------------------------
// Getters and Setters
//-----------------------------------------------
	
	public String getValue() {
		return value;
	}

	public void setColor(Color c) {
		textColor = c;
	}

}
