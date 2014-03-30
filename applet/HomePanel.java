package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Stroke;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JFrame;

public class HomePanel
{
	/**
	 * TODO:
	 * switch to nicer_but_slower_film_list table
	 */
	
	private Connection con;
	private JFrame frame;
	private int bannerHeight;
	
	
	public HomePanel() {
		setupConnection();
		frame = new JFrame("test");
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bannerHeight = 200;
	}
	
  public static void main( String args[] )
  {
	  /*JFrame frame = new JFrame("test");
	  frame.setSize(500,500);
	  frame.setVisible(true);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.add(new QueueView());
	  */
	  HomePanel home = new HomePanel();
	  //home.frame.add(new QueueView());
	  home.frame.add(home.setupMovieView("ACE GOLDFINGER"));
	  home.frame.validate();
	  
	  try
	  {
		  //con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sakila", "root", "" );
		  Statement stmt = home.con.createStatement();
		  ResultSet rs = stmt.executeQuery( "select * from film_text" );
      
		  while( rs.next() )
		  {
			 // System.out.println( rs.getString( "title" ) );
		  }
      
		  rs.close();
		  stmt.close();
      
		  GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  Font[] fonts = e.getAllFonts();
		  for (Font f : fonts) {
			 // System.out.println(f.getFontName());
		  }
		  //MovieView test = new MovieView(home);
		  //test.testPrint();
		 // home.gotoMovieView("Academy Dinosaur");
		  home.con.close();
    }
    catch( SQLException e )
    {
      e.printStackTrace();
    }
  }
  
  
  public void drawBackground(Graphics2D g2d) {
		g2d.setColor(parseHexColor("828282"));
		g2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g2d.setColor(parseHexColor("AEF100"));
		g2d.fillRect(4, 4, frame.getWidth(), bannerHeight);
		Stroke s = new BasicStroke(4.0f,   // Width
              BasicStroke.CAP_SQUARE,    // End cap
              BasicStroke.JOIN_MITER,    // Join style
              10.0f,                     // Miter limit
              new float[] {16.0f,20.0f}, // Dash pattern
              0.0f);                     // Dash phase
		g2d.setStroke(s);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(4, 4, frame.getWidth(), bannerHeight);
	}
  
  public Color parseHexColor(String hex) {
		int i = Integer.parseInt(hex, 16);
		return new Color(i);
	}
  
  /**
   * create a new movieView panel and add it to the content pane
   * 
   * @param title
   */
  public MovieView setupMovieView(String title) {
	  HashMap<String, String> hm = new HashMap<>();
	  ResultSet rs = createResultSet("SELECT * FROM nicer_but_slower_film_list WHERE title='" + title + "';");
	  
	  try {
		  rs.first();
		/*  System.out.println(rs.getString("title"));
		  System.out.println(rs.getString("description"));
		  System.out.println(rs.getString("category"));
		  System.out.println(rs.getString("actors"));
		  */
		  hm.put("title", rs.getString("title"));
		  hm.put("desc", rs.getString("description"));
		  hm.put("genre", rs.getString("category"));
		  hm.put("actors", rs.getString("actors"));
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  //frame.add( new MovieView(this, hm));
	  //System.out.println("frame add");
	 // frame.add(new QueueView());
	  return new MovieView(this, hm);
	  //test.testPrint();
  }
  
  public void gotoActorView(String actor) {
	  
  }
  
  public void gotoQueueView(String title) {
	  //ResultSet rs = createResultSet(query)
	  
  }
  
  
  
  private void setupConnection() {
	  try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sakila", "root", "" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
  
  private ResultSet createResultSet(String query) {
	  Statement stmt;
	  ResultSet rs;
	  try {
		  stmt = con.createStatement();
		  rs = stmt.executeQuery(query);
		  return rs;
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	return null;
  }
  
  
  public Connection getCon() {
	  return con;
  }
  
  public JFrame getFrame() {
	  return frame;
  }
  
}