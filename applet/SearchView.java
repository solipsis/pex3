package applet;

import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchView extends JComponent implements ActionListener {
	/**
	 * TODO:
	 * 
	 * Search bars (might have to use swing text boxes for this)
	 * basic search searches either name or words in description
	 * advanced search
	 * 		Options: actors, genre, rating
	 * 		use the generate result set I have in the home panel class	
	 * 
	 * once you have results add buttons for sorting
	 * 		alphabetical, by actor, genre, etc
	 * 
	 * when you hover over a result show a popup that has movie subscription
	 * 		steal the hover code form my MovieView class / actor class
	 * 		will need to implement MouseListener and MouseMotionListener and use mouse Moved function
	 * 
	 * create a class called clickable link or something that will be the results from the queries
	 * 		add Box stuff link I did in Actor so that you can use a Rectangle as bounding box for detecting if 
	 * 			mouse is hovering
	 * 		add text color change stuff like i did on hover in MovieView class.
	 * 		
	 * 
	 */

	private HomePanel home;
	private JList optionList;
	private JTextField textBox;
	private JPanel searchOptions;
	private Checkbox param_Title, param_Desc, param_Actor, param_Genre, param_Rating;
	private String searchParams;
	private ResultSet searchResults;

	public SearchView(HomePanel home) {
		this.home = home;
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(new Font("Pescadero", Font.PLAIN, 30));

		home.drawBackground(g2d);

		drawSearchBar();
		drawSearchOptions();
		home.getFrame().validate();
	}

	private void drawSearchBar() {
		textBox = new JTextField("Yo. Kanye West.", 100);
		textBox.setLocation(100, 200);
		textBox.setSize(400, 30);
		textBox.addActionListener(this);
		add(textBox);
	}

	private void drawSearchOptions() {
		searchOptions = new JPanel();
		searchOptions.setLayout(new GridLayout(5, 1));

		param_Title = new Checkbox("Title", null, true);
		param_Desc = new Checkbox("Description", null, true);
		param_Actor = new Checkbox("Actor", null, false);
		param_Genre = new Checkbox("Genre", null, false);
		param_Rating = new Checkbox("Rating", null, false);

		searchOptions.add(param_Title);
		searchOptions.add(param_Desc);
		searchOptions.add(param_Actor);
		searchOptions.add(param_Genre);
		searchOptions.add(param_Rating);

		searchOptions.setLocation(500, 200);
		searchOptions.setSize(100, 100);
		add(searchOptions);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		generateSearchParams();
		generateSQLSearch();
	}

	private void generateSearchParams() {
		searchParams = new String("");
		ArrayList<String> paramList = new ArrayList<String>();
		if (param_Title.getState()) {
			paramList.add("title");
		}
		if (param_Desc.getState()) {
			paramList.add("description");
		}
		if (param_Actor.getState()) {
			paramList.add("actor");
		}
		if (param_Genre.getState()) {
			paramList.add("genre");
		}
		if (param_Rating.getState()) {
			paramList.add("rating");
		}

		searchParams = "";
		for (String param : paramList) {
			if (searchParams == "") {
				searchParams += " " + param;
			} else {
				searchParams += " OR " + param;
			}
			searchParams += " LIKE '%" + textBox.getText() + "%'";
		}
	}

	private void generateSQLSearch() {
		String sqlQuery = "SELECT * FROM nicer_but_slower_film_list WHERE" + searchParams;
		searchResults = home.createResultSet(sqlQuery);
	}

}
