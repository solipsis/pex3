package applet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class MovieView extends JComponent implements MouseListener  {
	/** 
	 * To do:
	 * print movie info
	 * print list of actors
	 * 
	 */

	private HomePanel home;
	private HashMap<String, String> hm;
	private ArrayList<String> actors;
	private BufferedImage testImage;
	
	public MovieView(HomePanel home, HashMap<String, String> hm) {
		this.home = home;
		this.hm = hm;
		actors = new ArrayList<String>(Arrays.asList(hm.get("actors").split(", ")));
		addMouseListener(this);
		testImage = null;
		try {
			testImage = ImageIO.read( new File("images/cage1.jpg"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	
	}
	
	
	/**
	 * Paints the movie info
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// swing fonts suck. So I chose aesthetically pleasing ones
		g2d.setFont(new Font("Pescadero", Font.PLAIN, 30));
		
		// turn on antialiasing for sexy looking text
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		
		g2d.drawString(hm.get("title"), 20, 20);
		g2d.drawString("description", 100, 100);
		
		g2d.drawString("Your movie queue", 100, 100);
		repaint();
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	private static final long serialVersionUID = 1L;
}
