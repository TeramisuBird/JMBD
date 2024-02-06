import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeriesTest
{

  Series SeriesTest = new Series("xjhmefj12", "noLinkHere", "amogus", "susGame", "horror, mystery", "NR", "Jeff Bezos, Winston Chruchill");
  String SeriesTestStr = "amogus susGame\nNR horror, mystery\nJeff Bezos, Winston Chruchill";
  
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
  }
}
