import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

public class Main
{
  static final String APPID = "com.github.jmdb_f22_team11";
  
  /**
   * Starts and coordinates the program.
   */
  public static void main(String[] args)
  {
    boolean alreadyRunning;
    try { // Unique instance verification using JUnique mutex
      JUnique.acquireLock(APPID);
      alreadyRunning = false;
    } catch (AlreadyLockedException e) {
      alreadyRunning = true;
    }
    if(!alreadyRunning) { // Program start
      new Controller(new Model(), new View());
    }
  }
  
  public static void refreshGui(Model oldModel, View oldView, ArrayList<Media> searchResults, List<Media> favoritesList) {
    Point p = oldView.frame.getLocationOnScreen();
    new Controller(p, oldModel, searchResults, favoritesList);
    oldView.frame.dispose();
  }
}
