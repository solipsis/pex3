package applet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

public class QueueView extends JComponent implements MouseListener, MouseMotionListener {

	
	/**
	 * TODO: add delete button to movies
	 * check for delete when selecting movie
	 * finalize button to update order of queue
	 */
	
	
	/** Contains all the movies in the queue */
	ArrayList<DraggableMovie> movies = new ArrayList<DraggableMovie>();
	/** The currently selected movie */
	DraggableMovie selected;
	
	
	/** 
	 * Constructs a new QueueView panel
	 * adds Mouse listeners
	 */
	public QueueView() {
		movies.add(new DraggableMovie(20, 20, "Advanced Java"));
		addMouseListener(this);
		addMouseMotionListener(this);
		selected = null;
	}

	/**
	 * Paints each of the movies
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// swing fonts suck. So I choose aesthetically pleasing ones
		g2d.setFont(new Font("Pescadero", Font.PLAIN, 30));
		
		// turn on antialiasing for sexy looking text
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		
		g2d.drawString("Your movie queue", 100, 100);
		// paint the movies
		for (DraggableMovie d : movies) {
			d.paintComponent(g);
		}
	}
	
	
	/** 
	 * Check and see if the mouse is pressed on a movie,
	 * and set that movie to the selected movie
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		for (DraggableMovie d : movies) {
			if (d.getRect().contains(e.getX(), e.getY())) {
				selected = d;
				System.out.println("selected");
				break;
			}
		}
	}

	
	 /**
	  * Unselect the selected movie when the mouse is released 
	  */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		selected = null;
	}


	/**
	 * While the mouse is dragged, update the position,
	 * and collision box of the movie. Then repaint the panel
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (selected == null) {
			return;
		}
		selected.setRect(e.getX(), e.getY());
		selected.setX(e.getX() - 5);
		selected.setY(e.getY() - 5);
		repaint();
	}

	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
	private static final long serialVersionUID = 1L;
	
}
