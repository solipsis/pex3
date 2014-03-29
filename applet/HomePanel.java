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
      MovieView test = new MovieView(home);
      test.testPrint();
      home.con.close();
    }
    catch( SQLException e )
    {
      e.printStackTrace();
    }
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
  
  public Connection getCon() {
	  return con;
  }
  
}