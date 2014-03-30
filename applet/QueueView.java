package applet;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class QueueView extends JComponent implements MouseListener, MouseMotionListener {

	ArrayList<DraggableMovie> movies = new ArrayList<DraggableMovie>();
	DraggableMovie selected;
	
	public QueueView() {
		movies.add(new DraggableMovie(20, 20, "Advanced Java"));
		addMouseListener(this);
		addMouseMotionListener(this);
		selected = null;
	}
	
	
	public void paintComponent(Graphics g) {
		for (DraggableMovie d : movies) {
			d.paintComponent(g);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		selected = null;
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		if (selected == null) {
			return;
		}
		
		System.out.println("drag");
		/*for (DraggableMovie d : movies) {
			System.out.println(d.getRect().getX() + " : " + d.getRect().getY());
			if (d.getRect().contains(e.getX(), e.getY())) {
				d.setRect(e.getX(), e.getY());
				d.setX(e.getX() - 5);
				d.setY(e.getY() - 5);
				repaint();
				System.out.println("drag");
			}
		}
		*/
		selected.setRect(e.getX(), e.getY());
		selected.setX(e.getX() - 5);
		selected.setY(e.getY() - 5);
		repaint();
		
		
	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
