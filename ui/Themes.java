import java.awt.Color;
import javax.swing.ImageIcon;

public class Themes
{
  private static View view;
  public static Color foreground; // Foreground Color
  public static Color background; // Background Color
  public static Color accent;     // Accent color
  public static Color features;   // Features color
  
  public static View buildTheme(View oldView) {
    view = oldView;
    switch(Model.themeChosen) {
      case 1:
        foreground = Color.black;
        background = new Color(237, 231, 187);
        accent = null;
        features = null;
        setWindowImage(Model.ICON_PATH_TACOBELL);
        paintTheme(true);
        break;
      case 2:
        foreground = Color.white;
        background = Color.black;
        accent = Color.red;
        features = Color.pink;
        setWindowImage(Model.ICON_PATH_AMOGUS);
        paintTheme(false);
        break;
      default:
        setWindowImage(Model.ICON_PATH_DEFAULT);
        break;
    }
    return view;
  }
  
  static void setWindowImage(String path) {
      ImageIcon logo = new ImageIcon(path);
      view.frame.setIconImage(logo.getImage());
      view.imageLabel.setIcon(logo);
  }
  
  private static void paintTheme(boolean backgroundOnly) {
    Color f = foreground;
    Color b = background;
    Color a = accent;
    Color r = features;
    /* 
     * Frame + Panels + SubPanels
     */
    view.frame.getContentPane().setBackground(b);
    view.frame.getContentPane().setForeground(f);
    view.displayPanel.setBackground(b);
    view.displayPanel.setForeground(f);
    view.infoPanel.setBackground(b);
    view.infoPanel.setForeground(f);
    view.settingsPanel.setBackground(b);
    view.settingsPanel.setForeground(f);
    view.searchPanel.setBackground(b);
    view.searchPanel.setForeground(f);
    view.treePanel.setForeground(f);
    view.treePanel.setBackground(b);
    view.controlSub.setBackground(b);
    view.controlSub.setForeground(f);
    view.searchPanel.setBackground(b);
    view.searchPanel.setForeground(f);
    /*
     * Trees + ScrollPanes + Textboxes
     */
    view.favoritesView.setBackground(b);
    view.favoritesView.setForeground(f);
    view.treeView.setBackground(b);
    view.treeView.setForeground(f);
    view.tree.setBackground(b);
    view.tree.setForeground(f);
    view.favorites.setBackground(b);
    view.favorites.setForeground(f);
    view.searchBar.setBackground(b);
    view.searchBar.setForeground(f);
    /*
     * Labels 
     */
    view.titleLabel.setForeground(f);
    view.titleLabel.setBackground(b);
    view.infoLabel.setBackground(b);
    view.infoLabel.setForeground(f);
    view.imageLabel.setBackground(b);
    view.imageLabel.setForeground(f);
    /*
     * SubPanels
     */
    view.displaySub1.setForeground(f);
    view.displaySub1.setBackground(b);
    view.displaySub2.setForeground(f);
    view.displaySub2.setBackground(b);
    view.displaySub3.setForeground(f);
    view.displaySub3.setBackground(b);
    if(!backgroundOnly) {
      /* 
       * Buttons + Combo Boxes 
       */
      view.filterButton.setForeground(a);
      view.filterButton.setBackground(r);
      view.searchButton.setForeground(a);
      view.searchButton.setBackground(r);
      view.offlineModeButton.setForeground(a);
      view.offlineModeButton.setBackground(r);
      view.gameButton.setForeground(a);
      view.gameButton.setBackground(r);
      view.favoriteButton.setForeground(a);
      view.favoriteButton.setBackground(r);
      view.unfavoriteButton.setForeground(a);
      view.unfavoriteButton.setBackground(r);
      view.tempPopUp.setForeground(a);
      view.tempPopUp.setBackground(r);
      view.musicButton.setForeground(a);
      view.musicButton.setBackground(r);
      view.filterMenu.setForeground(a);
      view.filterMenu.setBackground(r);
      view.themeMenu.setForeground(a);
      view.themeMenu.setBackground(r);
    }
  }
}
