package applet;

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
	private HomePanel home;
	
	public ActorView(HomePanel home, String name, BufferedImage image, ArrayList<String> movies) {
		this.name = name;
		this.image = image;
		this.movies = movies;
		this.home = home;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// swing fonts suck. So I chose aesthetically pleasing ones
		g2d.setFont(new Font("Pescadero", Font.PLAIN, 30));
		
		// turn on antialiasing for sexy looking text
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		
		home.drawBackground(g2d);
		
		g2d.drawImage(image, home.getFrame().getWidth()/3, home.getBannerHeight()/2, null);
		
		
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

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
