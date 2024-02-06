import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Card extends JPanel {
  private static final long serialVersionUID = 1L;
  private String data;
  private String match;
  private JPanel panel;

  public Card(String data, String match) {
    this.data = data;
    this.match = match;

    panel = new JPanel();
    panel.setPreferredSize(new Dimension(50, 50));
    panel.setLayout(new GridLayout(1, 2));
  }

  public boolean checkMatch(Card card) {
    if (this.data.equals(card.match)) {
      return true;
    }

    return false;
  }

  public String getData() {
    return data;
  }

  public String getMatch() {
    return match;
  }

  public String toString() {
    return data;
  }
}
