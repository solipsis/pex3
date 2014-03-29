package applet;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePanel
{
	
	private Connection con;
	
	
	public HomePanel() {
		setupConnection();
	}
	
  public static void main( String args[] )
  {
    
	  HomePanel home = new HomePanel();
    try
    {
      //con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sakila", "root", "" );
      Statement stmt = home.con.createStatement();
      ResultSet rs = stmt.executeQuery( "select * from film_text" );
      
      while( rs.next() )
      {
        System.out.println( rs.getString( "title" ) );
      }
      
      rs.close();
      stmt.close();
      
      GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font[] fonts = e.getAllFonts();
      for (Font f : fonts) {
    	  System.out.println(f.getFontName());
      }
      //MovieView test = new MovieView(home);
      //test.testPrint();
      home.gotoMovieView("Academy Dinosaur");
      home.con.close();
    }
    catch( SQLException e )
    {
      e.printStackTrace();
    }
  }
  
  /**
   * create a new movieView panel and add it to the content pane
   * 
   * @param title
   */
  public void gotoMovieView(String title) {
	  //MovieView test = new MovieView(this, o);
	  // test.testPrint();
	  ResultSet rs = createResultSet("SELECT * FROM film_text WHERE title='" + title + "';");
	  MovieView test = new MovieView(this, rs);
	  test.testPrint();
  }
  
  public void gotoActorView(String actor) {
	  
  }
  
  public void gotoQueueView() {
	  
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
  
}