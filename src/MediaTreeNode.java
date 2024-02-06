import javax.swing.tree.DefaultMutableTreeNode;

public class MediaTreeNode extends DefaultMutableTreeNode 
{
  private static final long serialVersionUID = -6243211911999441039L;
  private final String shortString;
  
  public MediaTreeNode(Object UserObject, String shortString) {
    super(UserObject);
    this.shortString = shortString;
  }
  
  @Override
  public String toString() {
    return this.shortString;
  }

}
