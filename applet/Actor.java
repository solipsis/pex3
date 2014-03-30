package applet;

import java.awt.image.BufferedImage;

/**
 * simple class that holds an actor name and a picture
 */
public class Actor {
	
	private String name;
	private BufferedImage image;

	public Actor(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
