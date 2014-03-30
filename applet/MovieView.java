package applet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	//private ArrayList<BufferedImage> images;
	//private BufferedImage image;
	
	public MovieView(HomePanel home, HashMap<String, String> hm) {
		this.home = home;
		this.hm = hm;
		int rand;
		
		//actors = new ArrayList<String>(Arrays.asList(hm.get("actors").split(", ")));
		for (String name : hm.get("actors").split(", ")) {
			rand = (int) (Math.random() * home.getActorImages().size());
			actors.add(new Actor(name, home.getActorImages().get(rand)));
		}
		assignActorImages();
		//System.out.println(hm.get("description"));
		addMouseListener(this);
		
		/*
		image = null;
		try {
			image = ImageIO.read( new File("images/cage1.jpg"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		*/
	
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
		
		drawTitle(g2d);
		drawDescription(g2d);
		drawActors(g2d);
		
		repaint();
	}
	
	public void drawTitle(Graphics2D g2d) {
		g2d.setColor(home.parseHexColor("4791FF"));
		g2d.setFont(new Font("Pescadero", Font.BOLD, 50));
		g2d.drawString(hm.get("title"), home.getFrame().getWidth()/3, home.getBannerHeight()/2);
	}
	
	public void drawDescription(Graphics2D g2d) {
		g2d.setColor(home.parseHexColor("19D1D1"));
		g2d.setFont(new Font("Pescadero", Font.PLAIN, 25));
		g2d.drawString(hm.get("desc"), 50, home.getBannerHeight() + 50);
	}
	
	
	public void assignActorImages() {
		
	}
	
	public void drawActors(Graphics2D g2d) {
		int x = 20;
		int y = 300;

		for (Actor a : actors) {
		
			g2d.drawString(a.getName(), x, y);
			//g2d.drawImage(home.getActorImages().get(pic), x, y, 125, 150, null);
			g2d.drawImage(a.getImage(), x, y, 125, 150, null);
			
			x += 200;
		}
		//g2d.drawImage(image, -50, -50, 125, 150, null);
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
