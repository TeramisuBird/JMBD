import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * 
 * Joey's instructions for Controller expansion:
 * 
 * 1. Add your script / activate method under "Action Listeners"
 * 2. Call your script in buildActions().
 * 
 * If script modifies View greatly, simply create a new "Builder method".
 * Keep code concise and clean!
 * 
 * You may inherit from this class and expand on it.
 * 
 */
public class Controller {
	View view;
	Model model;

	/**
	 * ****** Constructor 1 - No parameter
	 */
	public Controller(Model model, View view) {
	  this.model = model;
	  this.view = view;
		Media defaultMedia = new Media(" ", " ", "Use search bar", " ");
		ArrayList<Media> defaultResults = new ArrayList<Media>();
		defaultResults.add(defaultMedia);
		model.mediaList = defaultResults;
		model.favoritesList = FavoritesList.readFavoritesList(
		    MovieJson.readJson(Model.PATH_FAVORITES));
		buildTree();
		buildFavorites();
		buildActions();
		generatePopUp(10);
    view = Themes.buildTheme(view);
		view.frame.setVisible(true);
		view.frame.pack();
	}

	/**
	 * ****** Constructor 2 - Has post-search parameter.
	 */
	public Controller(Point p, Model model, ArrayList<Media> searchResults, List<Media> favoritesList) {
		this.model = model;
		this.view = new View();
	  model.mediaList = searchResults;
		model.favoritesList = favoritesList;
		buildTree();
		buildFavorites();
		buildActions();
    generatePopUp(10);
    view = Themes.buildTheme(view);
		view.frame.setLocation(p);
		view.frame.setVisible(true);
    view.frame.pack();
	}
	
	public static void generatePopUp(int seconds) {
	  final int SECOND = 1000;
	  Timer popupTimer = new Timer();
	  popupTimer.schedule(new TimerTask() {
	    @Override
	    public void run() {
        Random rand = new Random();
	      if (Model.hasPopups) {
	        PopUpUI.createPopup();
	      }
        Controller.generatePopUp(rand.nextInt(32));
	    }
	  }, seconds*SECOND);
	}
	
	/************************************************
	 *           Action Listeners
	 *           
	 ***********************************************/
	void buildActions() {
	  activateSearch();
	  activateFilter();
	  activateFavorite();
	  activateUnfavorite();
	  activateGame();
	  activateOfflineModeToggle();
	  activateTreeSelection();
	  activateFavoritesSelection();
	  activateTreeDeselection(view.tree);
	  activateTreeDeselection(view.favorites);
	  activateThemeSelection();
	  activatePopUpToggle();
	  activateMusicToggle();
	}
	void activateMusicToggle() {
	  view.musicButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
        if (Model.isMuted) {
          view.musicButton.setText("BGM Playing");
          Model.isMuted = false;
          Sound.playMusic(Model.themeChosen);
        } else {
          view.musicButton.setText("BGM Muted");
          Model.isMuted = true;
          Sound.mute();
          Sound.muteEasterEgg();
        }
	    }
	  });
	}
	void activatePopUpToggle() {
	  view.tempPopUp.addActionListener(new ActionListener() {
	    @Override
      public void actionPerformed(ActionEvent e) {    
        if (Model.hasPopups) {
          view.tempPopUp.setText("Paid Premium ver");
          Model.hasPopups = false;
          if(!Model.isMuted) {
            Sound.muteEasterEgg();
          }
        } else {
          view.tempPopUp.setText("Free ver");
          Model.hasPopups = true;
          if(!Model.isMuted) {
            Sound.muteEasterEgg();
            Sound.playEasterEgg(Model.themeChosen);
          }
        }
      }
	  });
	}
	void activateThemeSelection() {
	  view.themeMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Sound.mute();
        String themeChosen = view.themeMenu.getSelectedItem().toString();
        switch(themeChosen) {
          case "Taco Bell":
            Model.themeChosen = 1;
            Themes.buildTheme(view);
            break;
          case "Among Us":
            Model.themeChosen = 2;
            Themes.buildTheme(view);
            break;
          default:
            Model.themeChosen = 0;
            Themes.buildTheme(view);
            break;
        }
        Sound.playSound(Model.themeChosen);
        if(!Model.isMuted) {
          Sound.playMusic(Model.themeChosen);
        }
        Main.refreshGui(model, view, (ArrayList<Media>)model.mediaList, model.favoritesList);
      }
    });
	}
	void activateTreeDeselection(JTree tree) {
	    tree.addFocusListener(new FocusListener() {
	      @Override
	      public void focusGained(FocusEvent e) { 
	      }
	      @Override
	      public void focusLost(FocusEvent e) {
	        tree.clearSelection();
	      }    
	    });
	    tree.addMouseListener(new MouseListener() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	        if(tree.getRowForLocation(e.getX(),e.getY()) == -1) {
	          tree.clearSelection();
	        }
	      }
	      @Override
	      public void mousePressed(MouseEvent e) { 
	      }
	      @Override
	      public void mouseReleased(MouseEvent e) {
	      }
	      @Override
	      public void mouseEntered(MouseEvent e) {
	      }
	      @Override
	      public void mouseExited(MouseEvent e) {
	      }
	    });
	  }
	 void activateSearch() {
     view.searchButton.addActionListener(new ActionListener() { // Button click-y action for search
        public void actionPerformed(ActionEvent e) {
          model.chosen = String.valueOf(view.filterMenu.getSelectedItem()); // Selected choice.
          ArrayList<Media> searchResults = null;
          if (model.chosen.equals("default") || model.chosen.equals("Simple Search")) {
            searchResults = (Model.isOffline) ? MovieJson.getMediaOffline(0)
                : MovieJson.getMediaOnline(0, view.searchBar.getText(), Model.filterString);
          } else if (model.chosen.equals("Search Movies")) {
            searchResults = (Model.isOffline) ? MovieJson.getMediaOffline(1)
                : MovieJson.getMediaOnline(1, view.searchBar.getText(), Model.filterString);
          } else if (model.chosen.equals("Search Series")) {
            searchResults = (Model.isOffline) ? MovieJson.getMediaOffline(2)
                : MovieJson.getMediaOnline(2, view.searchBar.getText(), Model.filterString);
          } else if (model.chosen.equals("Search People")) {
            searchResults = (Model.isOffline) ? MovieJson.getMediaOffline(3)
                : MovieJson.getMediaOnline(3, view.searchBar.getText(), null);
          }
          Main.refreshGui(model, view, searchResults, model.favoritesList);
        }
      });
  }
	void activateFilter() {
	   view.filterButton.addActionListener(new ActionListener() { // Button click-y action for applying filters
       public void actionPerformed(ActionEvent e) {
         if (!Model.filterOpen) {
           Filter.filterWindow();
           Model.filterOpen = true;
         }
       }
   });
	}
	void activateFavorite() {
	   view.favoriteButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        FavoritesList.addFavorite(model.selectedMovie, model.favoritesList);
	        Main.refreshGui(model, view, (ArrayList<Media>)model.mediaList, model.favoritesList);
	      }
	    });
	}
	void activateUnfavorite() {
	   view.unfavoriteButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        FavoritesList.removeFavorite(model.selectedFavorite, model.favoritesList);
	        Main.refreshGui(model, view, (ArrayList<Media>)model.mediaList, model.favoritesList);
	      }
	    });
	}
	 void activateGame() {
	    view.gameButton.addActionListener(new ActionListener() { // Button click-y action for search
	      public void actionPerformed(ActionEvent e) {
	        new MatchingGui(model.mediaList);
	      }
	    });
	  }
	void activateOfflineModeToggle() {
	   view.offlineModeButton.addActionListener(new ActionListener() { // Button click-y action for applying filters
	      public void actionPerformed(ActionEvent e) {
	        if (Model.isOffline) {
	          view.offlineModeButton.setText("Online");
	          Model.isOffline = false;
	        } else {
	          view.offlineModeButton.setText("Offline");
	          Model.isOffline = true;
	        }
	      }
	    });
	}
	void activateTreeSelection() {
	  view.tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Object selectedNode = e.getPath().getLastPathComponent();
        if (selectedNode instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
          Object selectedObject = node.getUserObject();
          if (selectedObject instanceof Media) {
            model.selectedMovie = (Media) selectedObject;
            String image = model.selectedMovie.getImage();
            view.titleLabel.setText(model.selectedMovie.toString());
            URL url;
            BufferedImage bufferImage = null;
            try {
              url = new URL(image);
              bufferImage = ImageIO.read(url);
              view.imageLabel.setIcon(new ImageIcon(bufferImage.getScaledInstance(
                  view.displayPanel.getWidth() - (view.displayPanel.getWidth() / 10),
                  view.displayPanel.getHeight() - (view.displayPanel.getHeight() / 10), Image.SCALE_SMOOTH)));
            } catch (Exception error) {
              url = null;
              bufferImage = null;
            }
          }
        }
      }
    });
	}
	void activateFavoritesSelection() {
	  view.favorites.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Object selectedNode = e.getPath().getLastPathComponent();
        if (selectedNode instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
          Object selectedObject = node.getUserObject();
          if (selectedObject instanceof Media) {
            model.selectedFavorite = (Media) selectedObject;
            String image = model.selectedFavorite.getImage();
            view.titleLabel.setText(model.selectedFavorite.toString());
            URL url;
            BufferedImage bufferImage = null;
            try {
              url = new URL(image);
              bufferImage = ImageIO.read(url);
              view.imageLabel.setIcon(new ImageIcon(bufferImage.getScaledInstance(
                  view.displayPanel.getWidth() - (view.displayPanel.getWidth() / 10),
                  view.displayPanel.getHeight() - (view.displayPanel.getHeight() / 10), Image.SCALE_SMOOTH)));
            } catch (Exception error) {
              url = null;
              bufferImage = null;
            }
          }
        }
      }
    });
	}

  /************************************************
   *            Builder methods
   * 
   ***********************************************/
	/**
	 * Builds the tree that is displayed on the side.
	 */
	private void buildTree() {
		SortedMap<String, List<Media>> groupedByTitle = new TreeMap<>();
		for (Media media : model.mediaList) {
			String title = media.basicString();
			if (groupedByTitle.containsKey(title)) {
				groupedByTitle.get(title).add(media);
			} else {
				List<Media> newList = new ArrayList<Media>();
				groupedByTitle.put(title, newList);
				newList.add(media);
			}
		}
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		for (String title : groupedByTitle.keySet()) {
			for (Media movie : groupedByTitle.get(title)) {
				MediaTreeNode mediaNode = new MediaTreeNode(movie, movie.basicString());
				rootNode.add(mediaNode);
			}
		}
		TreeModel model = new DefaultTreeModel(rootNode);
		view.tree.setModel(model);
		view.tree.setRootVisible(false);
	}

	/**
	 * Build the favorites list.
	 */
	private void buildFavorites() {
		SortedMap<String, List<Media>> groupedByTitle = new TreeMap<>();
		for (Media media : model.favoritesList) {
			String title = media.basicString();
			if (groupedByTitle.containsKey(title)) {
				groupedByTitle.get(title).add(media);
			} else {
				List<Media> newList = new ArrayList<Media>();
				groupedByTitle.put(title, newList);
				newList.add(media);
			}
		}
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		for (String title : groupedByTitle.keySet()) {
			for (Media movie : groupedByTitle.get(title)) {
				MediaTreeNode mediaNode = new MediaTreeNode(movie, movie.basicString());
				rootNode.add(mediaNode);
			}
		}
		TreeModel model = new DefaultTreeModel(rootNode);
		view.favorites.setModel(model);
		view.favorites.setRootVisible(false);
	}

}
