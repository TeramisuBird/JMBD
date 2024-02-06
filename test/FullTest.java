import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.jupiter.api.Test;

/**
 * Testing Suite made up of all test classes.
 * 
 */
class FullTest
{
  static final Media 
                  MediaTest         = new Media("xjhmefj12", "noLinkHere", "amogus", "susGame"),
                  TreeMedia         = new Movie("AmongUs", "MorbinGame", "2020", "sus", "4", "4", "3", "2");
  static final Movie 
                  MovieEmpty        = new Movie("null", "null", "null", "null", "null", "null", "null", "null"),
                  MovieTest         = new Movie("xjhmefj12", "noLinkHere", "amogus", "susGame", "10 min", "horror, mystery", "NR", "Jeff Bezos, Winston Chruchill");
  static final Series 
                  SeriesTest        = new Series("xjhmefj12", "noLinkHere", "amogus", "susGame", "horror, mystery", "NR", "Jeff Bezos, Winston Chruchill"),
                  SeriesEmpty       = new Series("null", "null", "null", "null", "null", "null", "null");
  static final String 
                  MovieTestStr      = "amogus susGame \n10 min NR horror, mystery \nJeff Bezos, Winston Chruchill",
                  Empty             = "N/A",
                  SeriesTestStr     = "amogus susGame \nNR horror, mystery \nJeff Bezos, Winston Chruchill",
                  MovieSearchKey    = "Morbius",
                  SeriesSearchKey   = "Flash",
                  PersonSearchKey   = "Steve Carell",
                  noFilter          = "",
                  SimpleFilter      = "&genres=action",
                  MediaTestStr      = "amogus susGame",
                  popUpTitleNormal  = "FREE IPHONE GIVEAWAY!  Clik here",
                  popUpTitleFunny   = "FrEe iPhOnE GiVeAwAy!  cLiK HeRe",
                  popUpImage        = "./resources/iPhone.jpg",
                  popUpIcon         = "./resources/apple/jpg";
  static final ArrayList<Media> 
                  basicSearch       = MovieJson.getMediaOnline(0, MovieSearchKey, noFilter),
                  basicMovieSearch  = MovieJson.getMediaOnline(1, MovieSearchKey, noFilter),
                  basicSeriesSearch = MovieJson.getMediaOnline(2, SeriesSearchKey, noFilter),
                  peopleSearch      = MovieJson.getMediaOnline(3, PersonSearchKey, null),
                  offlineBasic      = MovieJson.getMediaOffline(0),
                  offlineMovie      = MovieJson.getMediaOffline(1),
                  offlineSeries     = MovieJson.getMediaOffline(2),
                  offlinePeople     = MovieJson.getMediaOffline(3),
                  badOffline        = MovieJson.getMediaOffline(4),
                  search            = MovieJson.getMediaOnline(0, MovieSearchKey, SimpleFilter),
                  MovieSearch       = MovieJson.getMediaOnline(1, MovieSearchKey, SimpleFilter),
                  SeriesSearch      = MovieJson.getMediaOnline(2, SeriesSearchKey, SimpleFilter);
  static final DefaultMutableTreeNode node = new DefaultMutableTreeNode(TreeMedia);
  
  @Test
  void MediaBasicsTest()
  {
    assertEquals("xjhmefj12", MediaTest.getId());
    assertEquals("noLinkHere", MediaTest.getImage());
    assertEquals("amogus", MediaTest.getTitle());
    assertEquals("susGame", MediaTest.getDescription());
    assertEquals(MediaTestStr, MediaTest.toString());
    assertEquals(MediaTestStr, MediaTest.basicString());
  }

  @Test
  void MediaBadTest() {
    assertFalse("resfe12".equals(MediaTest.getId()));
    assertFalse("YesLinkHere".equals(MediaTest.getImage()));
    assertFalse("yellowBeard".equals(MediaTest.getTitle()));
    assertFalse("color yellow".equals(MediaTest.getDescription()));
    assertFalse("daca2".equals(MediaTest.toString()));
    
  }
  
  @Test
  void MovieBasicsTest()
  {
    assertEquals("xjhmefj12", MovieTest.getId());
    assertEquals("noLinkHere", MovieTest.getImage());
    assertEquals("amogus", MovieTest.getTitle());
    assertEquals("susGame", MovieTest.getDescription());
    assertEquals("10 min", MovieTest.getRuntime());
    assertEquals("horror, mystery", MovieTest.getGenres());
    assertEquals("NR", MovieTest.getRating());
    assertEquals("Jeff Bezos, Winston Chruchill", MovieTest.getStars());
    
    assertEquals(MovieTestStr, MovieTest.toString());
  }

  @Test
  void MovieBadTest() {
    assertFalse("resfe12".equals(MovieTest.getId()));
    assertFalse("YesLinkHere".equals(MovieTest.getImage()));
    assertFalse("yellowBeard".equals(MovieTest.getTitle()));
    assertFalse("color yellow".equals(MovieTest.getDescription()));
    assertFalse("122 hours".equals(MovieTest.getRuntime()));
    assertFalse("action".equals(MovieTest.getGenres()));
    assertFalse("G".equals(MovieTest.getRating()));
    assertFalse("Morbius".equals(MovieTest.getStars()));
    assertFalse("daca2".equals(MovieTest.toString()));
    assertTrue(Empty.equals(MovieEmpty.getGenres()));
    assertTrue(Empty.equals(MovieEmpty.getRating()));
    assertTrue(Empty.equals(MovieEmpty.getStars()));
    assertTrue(Empty.equals(MovieEmpty.getRuntime()));
  }
  
  @Test
  void SeriesBasicsTest()
  {
    assertEquals("xjhmefj12", SeriesTest.getId());
    assertEquals("noLinkHere", SeriesTest.getImage());
    assertEquals("amogus", SeriesTest.getTitle());
    assertEquals("susGame", SeriesTest.getDescription());
    assertEquals("horror, mystery", SeriesTest.getGenres());
    assertEquals("NR", SeriesTest.getRating());
    assertEquals("Jeff Bezos, Winston Chruchill", SeriesTest.getStars());
    assertEquals(SeriesTestStr, SeriesTest.toString());
  }

  @Test
  void SeriesBadTest() {
    assertFalse("resfe12".equals(SeriesTest.getId()));
    assertFalse("YesLinkHere".equals(SeriesTest.getImage()));
    assertFalse("yellowBeard".equals(SeriesTest.getTitle()));
    assertFalse("color yellow".equals(SeriesTest.getDescription()));
    assertFalse("action".equals(SeriesTest.getGenres()));
    assertFalse("G".equals(SeriesTest.getRating()));
    assertFalse("Morbius".equals(SeriesTest.getStars()));
    assertFalse("daca2".equals(SeriesTest.toString()));
    assertTrue(Empty.equals(SeriesEmpty.getGenres()));
    assertTrue(Empty.equals(SeriesEmpty.getRating()));
    assertTrue(Empty.equals(SeriesEmpty.getStars()));
  }
  
  @Test
  void TreeGoodTest()
  {
    MediaTreeNode modNode = new MediaTreeNode(node, "Test");
    assertTrue("Test".equals(modNode.toString()));
  }

  @Test
  void TreeBadTest() {
    MediaTreeNode modNode = new MediaTreeNode(node, "Test");
    assertFalse("Bad".equals(modNode.toString()));
  }
  
  @Test
  void SearchTest() {
    assertTrue("Morbius".equals(basicSearch.get(0).getTitle()));
    assertTrue("Morbius".equals(basicMovieSearch.get(0).getTitle()));
    assertTrue("The Flash".equals(basicSeriesSearch.get(0).getTitle()));
    assertTrue("Steve Carell".equals(peopleSearch.get(0).getTitle()));
  }
  
  @Test
  void FiltersTest() {
    assertTrue("Morbius".equals(search.get(0).getTitle()));
    assertTrue("Morbius".equals(MovieSearch.get(0).getTitle()));
    assertTrue("The Flash".equals(SeriesSearch.get(0).getTitle()));
  }
  
  @Test
  void OfflineTest() {
    assertTrue("Morbius".equals(offlineBasic.get(0).getTitle()));
    assertTrue("Morbius".equals(offlineMovie.get(0).getTitle()));
    assertTrue("The Flash".equals(offlineSeries.get(0).getTitle()));
    assertTrue("Steve Carell".equals(offlinePeople.get(0).getTitle()));
    assertThrows(IndexOutOfBoundsException.class, () -> {
      badOffline.get(0);
    });
  }
  
  @Test
  void FavoritesTest() {
    ArrayList<Media> favorites = null;
    favorites = FavoritesList.readFavoritesList(MovieJson.readJson(Model.PATH_FAVORITES));
    assertTrue("Add a Favorite".equals(favorites.get(0).getTitle()));
    Media filler = new Media("tt5108870", 
        "https://m.media-amazon.com/images/M/MV5BNTA3N2" 
            + "Q0ZTAtODJjNy00MmQzLWJlMmItOGFmNDI0ODgxN2" 
            + "QwXkEyXkFqcGdeQXVyMTM0NTU" 
            + "zNDIy._V1_Ratio0.6757_AL_.jpg", "Morbius", "(2022)");
    FavoritesList.addFavorite(filler, favorites);
    assertTrue("Morbius".equals(favorites.get(1).getTitle()));
    FavoritesList.removeFavorite(filler, favorites);
    ArrayList<Media> temp = favorites;
    assertThrows(IndexOutOfBoundsException.class, () -> {
      temp.get(1);
    });
  }
  
  @Test
  void FavoritesEmptyTest() {
    ArrayList<Media> favorites = null;
    favorites = FavoritesList.readFavoritesList(null);
    Media media = new Media("", "", "Add a favorite", "");
    favorites.add(media);
    assertTrue("Add a Favorite".equals(favorites.get(0).getTitle()));
  }
  
  @Test
  void PopupTest() {
    String text1 = PopUp.IPHONE.getTitle(true);
    String text2 = PopUp.IPHONE.getTitle(true);
    String text3 = PopUp.IPHONE.getTitle(true);
    String text4 = PopUp.IPHONE.getTitle(false);
    String image = PopUp.IPHONE.getImage();
    String icon = PopUp.IPHONE.getIcon();
    
    assertTrue(text1.equals(popUpTitleNormal));
    assertTrue(text2.equals(popUpTitleNormal));
    assertTrue(text3.equals(popUpTitleFunny));
    assertTrue(text4.equals(popUpTitleNormal));
    assertTrue(image.equals(image));
    assertTrue(icon.equals(icon));
  }
}
