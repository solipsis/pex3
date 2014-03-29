package applet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

public class MovieView extends JPanel  {
	
	private HomePanel home;
	
	public MovieView(HomePanel home) {
		this.home = home;
	}
	
	/** 
	 * To do:
	 * print movie info
	 * print list of actors
	 * 
	 */
	public void testPrint() {
		Statement stmt;
		try {
			stmt = home.getCon().createStatement();
			ResultSet rs = stmt.executeQuery( "select * from film_text where title='Academy Dinosaur' " );
			while (rs.next()) {
				System.out.println(rs.getString("description"));
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
