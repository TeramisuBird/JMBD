import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.text.MaskFormatter;

public class Filter {

	static public JFrame filterFrame;
	static public String filter;
	static private JPanel releaseDatePanel = new JPanel();
	static private JPanel userRatingPanel = new JPanel();
	static private JPanel genrePanel = new JPanel();
	static private JPanel companiesPanel = new JPanel();
	static private JPanel maturityRatingPanel = new JPanel();

	public static void filterWindow() {
		filterFrame = new JFrame();
		filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		filterFrame.setTitle("Filters");
		filterFrame.setSize(new Dimension(600, 337));
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new GridBagLayout());
		GridBagConstraints filterConstraints = new GridBagConstraints();
		filterConstraints.gridx = 1;
		filterConstraints.gridy = 0;

		/* Configuring releaseDatePanel */
		releaseDatePanel.setLayout(new GridBagLayout());

		/* Creating master HashMap */
		HashMap<String, HashMap<String, JComponent>> master = new HashMap<String, HashMap<String, JComponent>>();
		master.put("releaseDate", releaseDates());
		master.put("userRating", userRatings());
		master.put("genres", genres());
		master.put("companies", companies());
		master.put("maturityRating", maturityRatings());

		/* Creating the button that submits the filters */
		JButton submitFilters = new JButton("Apply Filters");
		submitFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.filterString = filterParse(master);
				Model.filterOpen = false;
				filterFrame.dispose();
				
			}
		});

		/* Adding panels to main filter panel */
		filterPanel.add(releaseDatePanel, filterConstraints);
		filterConstraints.gridy = 1;
		filterPanel.add(userRatingPanel, filterConstraints);
		filterConstraints.gridy = 2;
		filterPanel.add(new JLabel("Genres"), filterConstraints);
		filterConstraints.gridy = 3;
		filterPanel.add(genrePanel, filterConstraints);
		filterConstraints.gridy = 4;
		filterPanel.add(new JLabel("Companies"), filterConstraints);
		filterConstraints.gridy = 5;
		filterPanel.add(companiesPanel, filterConstraints);
		filterConstraints.gridy = 6;
		filterPanel.add(new JLabel("U.S. Certificates"), filterConstraints);
		filterConstraints.gridy = 7;
		filterPanel.add(maturityRatingPanel, filterConstraints);
		filterConstraints.gridy = 8;
		filterPanel.add(submitFilters, filterConstraints);
		JScrollPane scrollPane = new JScrollPane(filterPanel);
		filterFrame.add(scrollPane);
		filterFrame.setVisible(true);
	}

	public static HashMap<String, JComponent> releaseDates() {
		GridBagConstraints releaseDateConstraint = new GridBagConstraints();
		releaseDateConstraint.gridx = 1;
		releaseDateConstraint.gridy = 0;
		HashMap<String, JComponent> releaseDateFilters = new HashMap<String, JComponent>();
		MaskFormatter formatter;
		try {
			formatter = new MaskFormatter("####-##-##");
			formatter.setPlaceholder("_");
			releaseDateFilters.put("earliestDate", new JFormattedTextField(formatter));
			releaseDateFilters.put("latestDate", new JFormattedTextField(formatter));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JFrame errorWindow = new JFrame();
			errorWindow.setSize(new Dimension(200, 400));
			JLabel errorText = new JLabel("Invalid date(s), try again");
			errorWindow.add(errorText);
			errorWindow.setVisible(true);
			filterFrame.dispatchEvent(new WindowEvent(filterFrame, WindowEvent.WINDOW_CLOSING));
		}
		releaseDatePanel.add(new JLabel("Release date"), releaseDateConstraint);
		releaseDateConstraint.gridx = 0;
		releaseDateConstraint.gridy = 1;
		releaseDatePanel.add(releaseDateFilters.get("earliestDate"), releaseDateConstraint);
		releaseDateConstraint.gridx = 1;
		releaseDateConstraint.gridy = 1;
		releaseDatePanel.add(new JLabel("to"), releaseDateConstraint);
		releaseDateConstraint.gridx = 2;
		releaseDateConstraint.gridy = 1;
		releaseDatePanel.add(releaseDateFilters.get("latestDate"), releaseDateConstraint);
		releaseDateConstraint.gridx = 1;
		releaseDateConstraint.gridy = 2;
		releaseDatePanel.add(new JLabel("YYYY-MM-DD format"), releaseDateConstraint);

		return releaseDateFilters;
	}

	public static HashMap<String, JComponent> userRatings() {
		/* Configuring userRatingPanel */
		userRatingPanel.setLayout(new GridBagLayout());
		GridBagConstraints userRatingConstraint = new GridBagConstraints();
		userRatingConstraint.gridx = 1;
		userRatingConstraint.gridy = 0;
		HashMap<String, JComponent> userRatingFilters = new HashMap<String, JComponent>();
		Double[] lVal = new Double[101];
		Double[] hVal = new Double[101];
		for (int i = 0; i < 101; i++) {
			lVal[i] = (Math.floor((i * .1f) * 10) / 10);
			hVal[i] = (Math.floor((i * .1f) * 10) / 10);
		}
		final JComboBox<Double> lowerValues = new JComboBox<Double>(lVal);
		final JComboBox<Double> higherValues = new JComboBox<Double>(hVal);
		userRatingFilters.put("lowestRating", lowerValues);
		userRatingFilters.put("highestRating", higherValues);
		userRatingPanel.add(new JLabel("User Rating"), userRatingConstraint);
		userRatingConstraint.gridx = 0;
		userRatingConstraint.gridy = 1;
		userRatingPanel.add(lowerValues, userRatingConstraint);
		userRatingConstraint.gridx = 1;
		userRatingConstraint.gridy = 1;
		userRatingPanel.add(new JLabel("to"), userRatingConstraint);
		userRatingConstraint.gridx = 2;
		userRatingConstraint.gridy = 1;
		userRatingPanel.add(higherValues, userRatingConstraint);

		return userRatingFilters;
	}

	public static HashMap<String, JComponent> genres() {
		genrePanel.setLayout(new GridLayout(7, 4));
		HashMap<String, JComponent> genreFilters = new HashMap<String, JComponent>();
		genreFilters.put("action", new JToggleButton("Action", false));
		genreFilters.put("adventure", new JToggleButton("Adventure", false));
		genreFilters.put("animation", new JToggleButton("Animation", false));
		genreFilters.put("biography", new JToggleButton("Biography", false));
		genreFilters.put("comedy", new JToggleButton("Comedy", false));
		genreFilters.put("crime", new JToggleButton("Crime", false));
		genreFilters.put("documentary", new JToggleButton("Documentary", false));
		genreFilters.put("drama", new JToggleButton("Drama", false));
		genreFilters.put("family", new JToggleButton("Family", false));
		genreFilters.put("fantasy", new JToggleButton("Fantasy", false));
		genreFilters.put("film_noir", new JToggleButton("Film-Noir", false));
		genreFilters.put("game_show", new JToggleButton("Game Show", false));
		genreFilters.put("history", new JToggleButton("History", false));
		genreFilters.put("horror", new JToggleButton("Horror", false));
		genreFilters.put("music", new JToggleButton("Music", false));
		genreFilters.put("musical", new JToggleButton("Musical", false));
		genreFilters.put("mystery", new JToggleButton("Mystery", false));
		genreFilters.put("news", new JToggleButton("News", false));
		genreFilters.put("reality_tv", new JToggleButton("Reality TV", false));
		genreFilters.put("romance", new JToggleButton("Romance", false));
		genreFilters.put("sci_fi", new JToggleButton("Sci-Fi", false));
		genreFilters.put("sport", new JToggleButton("Sports", false));
		genreFilters.put("talk_show", new JToggleButton("Talk Show", false));
		genreFilters.put("thriller", new JToggleButton("Thriller", false));
		genreFilters.put("war", new JToggleButton("War", false));
		genreFilters.put("western", new JToggleButton("Western", false));
		for (String key : genreFilters.keySet()) {
			genrePanel.add(genreFilters.get(key));
		}
		return genreFilters;
	}

	public static HashMap<String, JComponent> companies() {
		companiesPanel.setLayout(new GridLayout(2, 4));
		HashMap<String, JComponent> companyFilters = new HashMap<String, JComponent>();
		companyFilters.put("fox", new JToggleButton("20th Century Fox", false));
		companyFilters.put("colombia", new JToggleButton("Sony", false));
		companyFilters.put("dreamworks", new JToggleButton("Dream Works", false));
		companyFilters.put("mgm", new JToggleButton("MGM", false));
		companyFilters.put("paramount", new JToggleButton("Paramount", false));
		companyFilters.put("universal", new JToggleButton("Universal", false));
		companyFilters.put("disney", new JToggleButton("Walt Disney", false));
		companyFilters.put("warner", new JToggleButton("Warner Bros.", false));
		for (String key : companyFilters.keySet()) {
			companiesPanel.add(companyFilters.get(key));
		}
		return companyFilters;
	}

	public static HashMap<String, JComponent> maturityRatings() {
		maturityRatingPanel.setLayout(new GridLayout(2, 4));
		HashMap<String, JComponent> maturityRatingFilters = new HashMap<String, JComponent>();
		maturityRatingFilters.put("us:G", new JToggleButton("G", false));
		maturityRatingFilters.put("us:PG", new JToggleButton("PG", false));
		maturityRatingFilters.put("us:PG-13", new JToggleButton("PG-13", false));
		maturityRatingFilters.put("us:R", new JToggleButton("R", false));
		maturityRatingFilters.put("us:NC-17", new JToggleButton("NC-17", false));

		maturityRatingPanel.add(maturityRatingFilters.get("us:G"));
		maturityRatingPanel.add(maturityRatingFilters.get("us:PG"));
		maturityRatingPanel.add(maturityRatingFilters.get("us:PG-13"));
		maturityRatingPanel.add(maturityRatingFilters.get("us:R"));
		maturityRatingPanel.add(maturityRatingFilters.get("us:NC-17"));

		return maturityRatingFilters;
	}

	@SuppressWarnings("unchecked")
	public static String filterParse(HashMap<String, HashMap<String, JComponent>> master) {
		String filterString = "";
		boolean isFirst = true;
		JToggleButton jtb;
		JFormattedTextField jtf;
		JComboBox<Double> jcb;

		/* Parsing userRating filter */
		ArrayList<Double> ratingInput = new ArrayList<Double>();
		for (String key : master.get("userRating").keySet()) {
			jcb = (JComboBox<Double>) master.get("userRating").get(key);
			ratingInput.add((double) jcb.getSelectedItem());
		}
		if (ratingInput.get(0) != 0.0 || ratingInput.get(1) != 0.0) {
			if (ratingInput.get(0) > ratingInput.get(1)) {
				filterString += "&user_rating=" + ratingInput.get(1) + "," + ratingInput.get(0);
			} else {
				filterString += "&user_rating=" + ratingInput.get(0) + "," + ratingInput.get(1);
			}
		}

		/* Parsing releaseDate filter */
		ArrayList<String> dateInput = new ArrayList<String>();
		for (String key : master.get("releaseDate").keySet()) {
			jtf = (JFormattedTextField) master.get("releaseDate").get(key);
			if (!jtf.getText().contains("_")) {
				dateInput.add(jtf.getText());
			}
		}
		if (dateInput.size() == 2) {
			if (Integer.parseInt(dateInput.get(0).substring(0, 4)) > Integer
					.parseInt(dateInput.get(1).substring(0, 4))) {
				filterString += "&release_date=" + dateInput.get(1) + "," + dateInput.get(0);
			} else {
				filterString += "&release_date=" + dateInput.get(0) + "," + dateInput.get(1);
			}
		}

		/* Parsing genre filter */
		for (String key : master.get("genres").keySet()) {
			jtb = (JToggleButton) master.get("genres").get(key);
			if (jtb.isSelected() && isFirst) {
				filterString += "&genres=" + key;
				isFirst = false;
			} else if (jtb.isSelected()) {
				filterString += "," + key;
			}
		}

		isFirst = true;
		/* Parsing company filters */
		for (String key : master.get("companies").keySet()) {
			jtb = (JToggleButton) master.get("companies").get(key);
			if (jtb.isSelected() && isFirst) {
				filterString += "&companies=" + key;
				isFirst = false;
			} else if (jtb.isSelected()) {
				filterString += "," + key;
			}
		}

		isFirst = true;
		/* Parsing maturityRating filter */
		for (String key : master.get("maturityRating").keySet()) {
			jtb = (JToggleButton) master.get("maturityRating").get(key);
			if (jtb.isSelected() && isFirst) {
				filterString += "&certificates=" + key;
				isFirst = false;
			} else if (jtb.isSelected()) {
				filterString += "," + key;
			}
		}
		return filterString;
	}
}
