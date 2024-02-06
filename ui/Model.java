import java.util.List;


/**
 * Add in any variable you would like persisting across entire Gui refreshes.
 * This does not persist on close .. for that use MovieJson.writeJson()
 * 
 * ~ Joey
 * 
 */
public class Model
{
  // General Settings
  static boolean isOffline = false;
  static boolean hasPopups = false;
  static boolean isMuted = true;
  
  // Image locations + Audio Locations + Frame Info
  public static final String ICON_PATH_DEFAULT = "./resources/defImage.jpg";
  public static final String ICON_PATH_TACOBELL = "./resources/jmdb_logo.jpg";
  public static final String ICON_PATH_AMOGUS = "./resources/amongus.jpg";
  public static final String AUDIO_PATH_TACOBELL_SELECT = "./resources/tacoBellSelected.wav";
  public static final String AUDIO_PATH_AMOGUS_SELECT = "./resources/amongusSelected.wav";
  public static final String AUDIO_PATH_AMOGUS = "./resources/amongusBGM.wav";
  public static final String AUDIO_PATH_AMOGUS_FUNNY = "./resources/AMOGUS.wav";
  public static final String AUDIO_PATH_TACOBELL = "./resources/tacoBellBGM.wav";
  public static final String AUDIO_PATH_TACOBELL_FUNNY = "./resources/tacoBellReview.wav";
  public static final String PATH_FAVORITES = "favorites-list.json";

  static final double ASPECT_RATIO = 16 / 10;
  
  // Search filtering
  public static final String[] FILTERS = { 
      "Simple Search", "Search Movies", "Search Series", "Search People" };
  public static String filterString = "";
  static boolean filterOpen = false;
  
  // Tree Movie selection
  public String chosen = "default";
  public boolean isFirstRun = true;
  public Media selectedMovie;
  public Media selectedFavorite;
  
  // Search
  public List<Media> mediaList;
  public List<Media> favoritesList;

  // Theme Menu
  public static final String[] THEMES = {
      "Default", "Taco Bell", "Among Us"};
  public static int themeChosen = 0;
}
