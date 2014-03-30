package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
	private ArrayList<BufferedImage> actorImages;
	
	
	
	public HomePanel() {
		setupConnection();
		frame = new JFrame("test");
		frame.setSize(1280,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bannerHeight = 200;
		actorImages = new ArrayList<BufferedImage>();
		loadActorImages();
	}
	
  public static void main( String args[] )
  {
	  
	  HomePanel home = new HomePanel();
	  //loadActorImages();
	  //home.frame.add(new QueueView());
	  home.frame.add(home.setupMovieView("ACE GOLDFINGER"));
	  // ensures the new panel is placed and drawn
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
  
  
  public void loadActorImages() {
	  
	  BufferedImage image = null;
	  for (int x = 1; x < 14; x++) {
		  image = null;
		  try {
			  image = ImageIO.read( new File("images/cage" + x + ".jpg"));
			  actorImages.add(image);
		  }
		  catch (IOException e) {
			  e.printStackTrace();
			  System.out.println(e.getMessage());
		  }
	  }
  }
  
  public void drawBackground(Graphics2D g2d) {
	  // background
	  g2d.setColor(parseHexColor("828282"));
	  g2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
	  // banner
	  g2d.setColor(parseHexColor("AEF100"));
	  g2d.fillRect(4, 4, frame.getWidth(), bannerHeight);
	  // banner border
	  Stroke s = new BasicStroke(4.0f, // Width
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
  
//-----------------------------------------------
//Getters and Setters
//-----------------------------------------------
  
  public Connection getCon() {
	  return con;
  }
  
  public JFrame getFrame() {
	  return frame;
  }
  
  public int getBannerHeight() {
	  return bannerHeight;
  }
  
  public ArrayList<BufferedImage> getActorImages() {
	  return actorImages;
  }
  
  
  
  
  
  
}