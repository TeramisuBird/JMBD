import static org.junit.jupiter.api.Assertions.*;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.jupiter.api.Test;

class MediaTreeNodeTest
{
  Media TreeMedia = new Movie("AmongUs", "MorbinGame", "2020", "sus", "4", "4", "3", "2");
  DefaultMutableTreeNode node = new DefaultMutableTreeNode(TreeMedia);

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
}
