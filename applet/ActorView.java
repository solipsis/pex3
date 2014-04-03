package applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;

public class ActorView extends JComponent implements MouseListener, MouseMotionListener {
	
	
	
	private String name;
	private BufferedImage image;
	private ArrayList<String> movies;
	private ArrayList<StringLink> movieLinks;
	private HomePanel home;
	
	public ActorView(HomePanel home, String name, BufferedImage image, ArrayList<String> movies) {
		addMouseMotionListener(this);
		addMouseListener(this);
		
		this.name = name;
		this.image = image;
		this.movies = movies;
		this.home = home;
		this.movieLinks = new ArrayList<StringLink>();
		int x = 50;
		int y = home.getBannerHeight() + 100;
		for (int i = 0; i < movies.size() / 2; i++) {
			movieLinks.add(new StringLink(x,y, 100, 10, movies.get(i)));
			y += 30;
		}
		x = 400;
		y = home.getBannerHeight();
		for (int i = movies.size() / 2; i < movies.size(); i++) {
			movieLinks.add(new StringLink(x,y, 100, 10, movies.get(i)));
			y += 30;
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// swing fonts suck. So I chose aesthetically pleasing ones
		g2d.setFont(new Font("Pescadero", Font.PLAIN, 30));
		
		// turn on antialiasing for sexy looking text
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		
		home.drawBackground(g2d);
		
		drawName(g2d);
		g2d.drawImage(image, home.getFrame().getWidth() - 500, home.getBannerHeight() + 70, 300,400, null);
		drawMovies(g2d);
		
		
		repaint();
	}
	
	public void drawMovies(Graphics2D g2d) {
		int x = 50;
		int y = home.getBannerHeight() + 100;
		
		g2d.setFont(new Font("Pescadero", Font.ITALIC, 40));
		g2d.drawString("Movies:", x + 200, y - 50);
		
		
		g2d.setFont(home.getPrimaryTextFont());
		g2d.setColor(home.getPrimaryTextColor());
		
		for (int i = 0; i < movies.size() / 2; i++) {
			g2d.drawString(movies.get(i), x, y);
			y += 30;
		}
		x = 400;
		y = home.getBannerHeight() + 100;
		for (int i = movies.size() / 2; i < movies.size(); i++) {
			g2d.drawString(movies.get(i), x, y);
			y += 30;
		}
	}
	
	/**
	 * draws the name of the actor
	 */
	public void drawName(Graphics2D g2d) {
		g2d.setColor(home.parseHexColor("4791FF"));
		g2d.setFont(new Font("Pescadero", Font.BOLD, 50));
		g2d.drawString(name, (home.getFrame().getWidth()/3) + 50, home.getBannerHeight()/2);
	}
	
	

	/**
	 * Detects if the mouse is over an actor and 
	 * highlights the actor if true
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("move");
		for (StringLink link : movieLinks) {
			if (link.getRect().contains(e.getX()+10, e.getY()+10)) {
				link.setColor(Color.RED);
			}
			else {
				link.setColor(home.getPrimaryTextColor());
			}
		}
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
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
