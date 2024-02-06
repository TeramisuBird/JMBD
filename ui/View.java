import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 * 
 * Joey's instructions for View expansion:
 * 
 * 1. Declare your entity, but do not initialize.
 * 2. Initialize in constructor, setting it equal to a make method & casted.
 * 3. Create your make method under it's respective category!
 * 
 * You may inherit from this class and expand on it.
 * 
 */
public class View
{
  public JFrame frame;
  public JOptionPane popUpFrame;
  public JPanel displayPanel, infoPanel, searchPanel, settingsPanel, 
  treePanel;
  // Sub-Panels
  public JPanel 
  displaySub1, displaySub2, displaySub3, // displayPanel
  controlSub; // searchPanel
  public JScrollPane treeView, favoritesView;
  public JLabel infoLabel, imageLabel, titleLabel;
  public final JTree tree, favorites;
  public JTextField searchBar;
  public JComboBox<String> filterMenu;
  public JComboBox<String> themeMenu;
  public JButton filterButton, searchButton, offlineModeButton, 
  gameButton, favoriteButton, unfavoriteButton, tempPopUp, musicButton;
  public Dimension 
    sizeSearchBar = new Dimension(300, 50),
    sizeTitle = new Dimension(300, 50),
    sizeApply = new Dimension(30, 30),
    sizeFavorite = new Dimension(95, 25),
    sizeDisplay = new Dimension(400, 500),
    sizeFrame = new Dimension(900, 900);
  
  /*************************
   *        Constructor
   *        
   *************************/
  @SuppressWarnings("unchecked")
  public View() {
    buildSubPanels();
    filterMenu        = (JComboBox<String>) makeFilterMenu();
    themeMenu         = (JComboBox<String>) makeThemeMenu();
    searchBar         = (JTextField) makeSearchBar();
    tree              = (JTree) makeTree();
    favorites         = (JTree) makeFavorites();
    treeView          = (JScrollPane) makeTreeView();
    favoritesView     = (JScrollPane) makeFavoritesView();
    offlineModeButton = (JButton) makeOfflineModeButton();
    filterButton      = (JButton) makeFilterButton();
    searchButton      = (JButton) makeSearchButton();
    gameButton        = (JButton) makeGameButton();
    favoriteButton    = (JButton) makeFavoriteButton();
    unfavoriteButton  = (JButton) makeUnfavoriteButton();
    tempPopUp         = (JButton) makePopUpButton();
    musicButton       = (JButton) makeMusicButton();
    infoLabel         = (JLabel) makeInfoLabel();
    titleLabel        = (JLabel) makeTitleLabel();
    imageLabel        = (JLabel) makeImageLabel();
    controlSub        = (JPanel) makeControlSub();
    settingsPanel     = (JPanel) makeSettingsPanel();
    infoPanel         = (JPanel) makeInfoPanel();
    searchPanel       = (JPanel) makeSearchPanel();
    displayPanel      = (JPanel) makeDisplayPanel();
    treePanel         = (JPanel) makeTreePanel();
    frame             = (JFrame) makeFrame();
  }
  
  /*****************************
   *        Components
   *        
   *****************************/
  private Component makeFilterMenu() {
    JComboBox<String> x = new JComboBox<String>(Model.FILTERS);
    return x;
  }
  private Component makeThemeMenu() {
    JComboBox<String> x = new JComboBox<String>(Model.THEMES);
    x.getModel().setSelectedItem(Model.THEMES[Model.themeChosen]);
    return x;
  }
  private Component makeSearchBar() {
    JTextField t = new JTextField();
    t.setSize(sizeSearchBar);
    return t;
  }
  /*************************
   *        Trees
   *        
   *************************/
  private Component makeTree() {
    return new JTree();
  }
  private Component makeFavorites() {
    return new JTree();
  }
  /*************************
   *       Scroll-Panes
   *        
   *************************/
  private Component makeTreeView() {
    return new JScrollPane(tree);
  }
  private Component makeFavoritesView() {
    return new JScrollPane(favorites);
  }
  /*************************
   *        Buttons
   *        
   *************************/
  private Component makeOfflineModeButton() {
    return new JButton(
        (Model.isOffline) ? "Offline" : "Online");
  }
  private Component makeFilterButton() {
    JButton b = new JButton("Filter");
    b.setSize(sizeApply);
    return b;
  }
  private Component makeSearchButton() {
    return new JButton("Search");
  }
  private Component makeGameButton() {
    return new JButton("Play Game");
  }
  private Component makeFavoriteButton() {
    JButton b = new JButton("Favorite");
    b.setPreferredSize(sizeFavorite);
    return b;
  }
  private Component makeUnfavoriteButton() {
    JButton b = new JButton("Unfavorite");
    b.setPreferredSize(sizeFavorite);
    return b;
  }
  private Component makePopUpButton() {
    JButton b = new JButton("Paid Premium ver");
    return b;
  }
  private Component makeMusicButton() {
    JButton b = new JButton((Model.isMuted)? "BGM Muted" : "BGM Playing");
    return b;
  }
  /*************************
   *        Labels
   *        
   *************************/
  private Component makeInfoLabel() {
    JLabel l = new JLabel();
    l.setPreferredSize(sizeTitle);
    return l;
  }
  private Component makeTitleLabel() {
    JLabel l = new JLabel();
    l.setPreferredSize(sizeTitle);
    return l;
  }
  private Component makeImageLabel() {
    return new JLabel();
  }
  /*************************
   *        Sub-Panels
   *        
   *************************/
  private Component makeControlSub() {
    JPanel s = new JPanel(new FlowLayout());
    s.add(searchButton);
    s.add(filterMenu);
    s.add(filterButton);
    s.add(favoriteButton);
    s.add(unfavoriteButton);
    return s;
  }
  
  /*************************
   *        Panels
   *        
   *************************/
  private Component makeSettingsPanel() {
    JPanel p = new JPanel(new FlowLayout());
    p.add(offlineModeButton);
    p.add(gameButton);
    p.add(themeMenu);
    p.add(tempPopUp);
    p.add(musicButton);
    return p;
  }
  private Component makeInfoPanel() {
    JPanel p = new JPanel(new BorderLayout());
    p.add(infoLabel, BorderLayout.NORTH);
    p.add(imageLabel, BorderLayout.CENTER);
    p.add(titleLabel, BorderLayout.SOUTH);
    return p;
  }
  /** 
   * Creates a panel with search functionality.
   * Holds search-bar, buttons, drop-down menu and more!
   */
  private Component makeSearchPanel() {
    JPanel p = new JPanel(new BorderLayout());
    p.add(searchBar, BorderLayout.CENTER);
    p.add(controlSub, BorderLayout.EAST);
    return p;
  }
  /** 
   * Creates a panel that displays media/movie info.
   */
  private Component makeDisplayPanel() {
    JPanel p = new JPanel(new BorderLayout());
    p.setPreferredSize(sizeDisplay);
    
    /* Sub Insertion */
    displaySub1.add(imageLabel, BorderLayout.CENTER);
    displaySub2.add(infoPanel, BorderLayout.CENTER);
    displaySub3.add(searchPanel, BorderLayout.CENTER);
    /* Sub Layering */
    displaySub1.add(displaySub2, BorderLayout.SOUTH);
    displaySub2.add(displaySub3, BorderLayout.SOUTH);
    
    p.add(displaySub1);
    return p;
  }
  /** 
   * Creates a panel that holds scroll-panes for trees.
   */
  private Component makeTreePanel() {
    JPanel t = new JPanel(new GridLayout(2, 1));
    t.add(treeView);
    t.add(favoritesView);
    return t;
  }
  
  /*************************
   *        Frames
   *        
   *************************/
  /**
   * Builds the overall interface of everything.
   */
  private Component makeFrame() {
    JFrame f = new JFrame();
    ImageIcon i = new ImageIcon(Model.ICON_PATH_TACOBELL);
    f.setIconImage(i.getImage());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(treePanel, BorderLayout.WEST);
    f.add(displayPanel, BorderLayout.CENTER);
    f.add(settingsPanel, BorderLayout.NORTH);
    f.setPreferredSize(sizeFrame);
    f.setBackground(new Color(237, 231, 187));
    return f;
  }
  
  private void buildSubPanels() {
    displaySub1 = new JPanel(new BorderLayout());
    displaySub2 = new JPanel(new BorderLayout());
    displaySub3 = new JPanel(new BorderLayout());
  }

}