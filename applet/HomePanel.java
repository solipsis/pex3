package applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class HomePanel extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TODO: switch to nicer_but_slower_film_list table
	 */

	private Connection con;
	private JFrame frame;
	private int bannerHeight;
	private ArrayList<BufferedImage> actorImages;
	static Color bannerColor;
	static Color bannerTextColor;
	static Color backgroundColor;
	static Color primaryTextColor;

	public HomePanel() {
		setupConnection();
		frame = new JFrame("test");
		frame.setSize(1280, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bannerHeight = 200;
		actorImages = new ArrayList<BufferedImage>();
		loadActorImages();
		createColors();
	}

	public void init() {
		run();
	}

	// public static void main( String args[] )
	public void run() {

		

		// uncomment one of the following 3 lines to test that view
		// home.frame.add(new QueueView());
		 frame.add(setupMovieView("ACE GOLDFINGER"));
		//frame.add(gotoActorView("Chris Depp"));
		//frame.add(setupSearchView());
		// home.frame.add(new ActorView());

		// ensures the new panel is placed and drawn
		frame.validate();
	}

	/**
	 * Uses console to display all fonts available to this particular computer
	 */
	public void printAvailableFonts() {
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();
		for (Font f : fonts) {
			System.out.println(f.getFontName());
		}
	}

	/**
	 * loads the actor images from the image file
	 */
	public void loadActorImages() {
		BufferedImage image = null;

		for (int x = 1; x < 14; x++) {
			image = null;
			try {
				// image paths must be from a relative url when running 
				// an applet instead of applicaiton
				Class<? extends HomePanel> cls = getClass();
				URL url = cls.getResource("images/cage" + x + ".jpg");
				image = ImageIO.read(url);
				actorImages.add(image);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Draws the general layout of the page. Includes the background, a top
	 * banner, and a border around the banner
	 */
	public void drawBackground(Graphics2D g2d) {
		// background
		g2d.setColor(parseHexColor("828282"));
		g2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		// banner
		g2d.setColor(parseHexColor("AEF100"));
		g2d.fillRect(4, 4, frame.getWidth(), bannerHeight);
		// banner border
		Stroke s = new BasicStroke(4.0f, // Width
				BasicStroke.CAP_SQUARE, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 16.0f, 20.0f }, // Dash pattern
				0.0f); // Dash phase
		g2d.setStroke(s);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(4, 4, frame.getWidth(), bannerHeight);
	}

	private void createColors() {
		bannerColor = parseHexColor("AEF100");
		bannerTextColor = parseHexColor("4791FF");
		backgroundColor = parseHexColor("828282");
		primaryTextColor = parseHexColor("19D1D1");
	}

	/**
	 * Turns a hex string into an int that can be used to contstruct a new Color
	 * 
	 * @param hex
	 * @return a Color
	 */
	public Color parseHexColor(String hex) {
		int i = Integer.parseInt(hex, 16);
		return new Color(i);
	}

	/**
	 * create a new movieView panel and add it to the content pane required info
	 * is stored in a hashmap
	 * 
	 * @param title
	 */
	public MovieView setupMovieView(String title) {
		HashMap<String, String> hm = new HashMap<>();
		ResultSet rs = createResultSet("SELECT * FROM nicer_but_slower_film_list WHERE title='"
				+ title + "';");

		try {
			rs.first();
			hm.put("title", rs.getString("title"));
			hm.put("desc", rs.getString("description"));
			hm.put("genre", rs.getString("category"));
			hm.put("actors", rs.getString("actors"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new MovieView(this, hm);
	}

	/**
	 * set the main content pane to a new ActorView
	 * 
	 * @param actor
	 * @return
	 */
	public Component gotoActorView(String actor) {
		
		String query = "SELECT * FROM nicer_but_slower_film_list WHERE actors LIKE \"%" + actor + "%\";";
		System.out.println(query);
		//ResultSet rs = createResultSet("SELECT * FROM nicer_but_slower_film_list WHERE CONTAINS(actors, '" + actor + "');");
		ResultSet rs = createResultSet(query);
		ArrayList<String> movies = new ArrayList<String>();

		try {
			System.out.println("first");
			rs.first();
			//rs.get
			while (rs.next()) {
				System.out.println("a");
				System.out.println(rs.getString("title"));
				movies.add(rs.getString("title"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ActorView(this, actor, actorImages.get(0), movies);
	}

	private Component setupSearchView() {
		return new SearchView(this);

	}

	/**
	 * Set the main content pane to a new QueueView
	 * 
	 * @param title
	 */
	public void gotoQueueView(String title) {
		// TODO:
	}

	/**
	 * Sets up the connection to the database
	 */
	private void setupConnection() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sakila", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a result set from a sql string query
	 * 
	 * @param query
	 * @return a sql result set
	 */
	public ResultSet createResultSet(String query) {
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

	// -----------------------------------------------
	// Getters and Setters
	// -----------------------------------------------

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