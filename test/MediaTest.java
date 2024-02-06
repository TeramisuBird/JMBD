import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MediaTest
{
  Media test = new Media("xjhmefj12", "noLinkHere", "amogus", "susGame");
  String testStr = "amogus susGame";
  
  @Test
  void basicsTest()
  {
    assertEquals("xjhmefj12", test.getId());
    assertEquals("noLinkHere", test.getImage());
    assertEquals("amogus", test.getTitle());
    assertEquals("susGame", test.getDescription());
    
    assertEquals(testStr, test.toString());
  }

  @Test
  void badTest() {
    assertFalse("resfe12".equals(test.getId()));
    assertFalse("YesLinkHere".equals(test.getImage()));
    assertFalse("yellowBeard".equals(test.getTitle()));
    assertFalse("color yellow".equals(test.getDescription()));
    
    assertFalse("daca2".equals(test.toString()));
  }
}
