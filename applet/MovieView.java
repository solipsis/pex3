package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class MovieView extends JComponent implements MouseListener, MouseMotionListener  {
	/** 
	 * To do:
	 * print movie info
	 * print list of actors
	 * 
	 */

	private HomePanel home;
	private HashMap<String, String> hm;
	private ArrayList<String> actors;
	private BufferedImage image;
	
	public MovieView(HomePanel home, HashMap<String, String> hm) {
		this.home = home;
		this.hm = hm;
		actors = new ArrayList<String>(Arrays.asList(hm.get("actors").split(", ")));
		addMouseListener(this);
		image = null;
		try {
			image = ImageIO.read( new File("images/cage1.jpg"));
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
		
		home.drawBackground(g2d);
		//drawBackground(g2d);
		drawTitle(g2d);
		drawDescription(g2d);
		drawActors(g2d);
		
		repaint();
	}
	
	public void drawTitle(Graphics2D g2d) {
		g2d.setColor(parseHexColor("4791FF"));
		g2d.setFont(new Font("Pescadero", Font.BOLD, 50));
		g2d.drawString(hm.get("title"), 100, 100);
	}
	
	public void drawDescription(Graphics2D g2d) {
		g2d.drawString("description", 100, 200);
	}
	
	public void drawActors(Graphics2D g2d) {
		
	
		g2d.drawImage(image, -50, -50, 125, 150, null);
	}
	
	public void drawBackground(Graphics2D g2d) {
		g2d.setColor(parseHexColor("828282"));
		g2d.fillRect(0, 0, home.getFrame().getWidth(), home.getFrame().getHeight());
		g2d.setColor(parseHexColor("AEF100"));
		g2d.fillRect(4, 4, home.getFrame().getWidth(), 200);
		Stroke s = new BasicStroke(4.0f,   // Width
                BasicStroke.CAP_SQUARE,    // End cap
                BasicStroke.JOIN_MITER,    // Join style
                10.0f,                     // Miter limit
                new float[] {16.0f,20.0f}, // Dash pattern
                0.0f);                     // Dash phase
		g2d.setStroke(s);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(4, 4, home.getFrame().getWidth(), 200);
	}
	
	public Color parseHexColor(String hex) {
		int i = Integer.parseInt(hex, 16);
		return new Color(i);
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
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private static final long serialVersionUID = 1L;



}
