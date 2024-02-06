import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieTest
{

  Movie MovieTest = new Movie("xjhmefj12", "noLinkHere", "amogus", "susGame", "10 min", "horror, mystery", "NR", "Jeff Bezos, Winston Chruchill");
  String MovieTestStr = "amogus susGame\n10 min NR\nhorror, mystery Jeff Bezos, Winston Chruchill";
  
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
  }
}
