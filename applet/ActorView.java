package applet;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

	public void drawComponent(Graphics2D g2d) {
		g2d.drawImage(image, home.getFrame().getWidth()/3, home.getBannerHeight()/2, null);
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
	
	
}
