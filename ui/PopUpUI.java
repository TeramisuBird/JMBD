import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PopUpUI
{
  public static void createPopup() {
    Random ran = new Random();
    int val = ran.nextInt(6);
    String imageLoc = "";
    String iconLoc = "";
    String text = "";
    switch (val) {
      case 0:
        imageLoc = PopUp.GIFTCARD.getImage();
        iconLoc = PopUp.GIFTCARD.getIcon();
        text = PopUp.GIFTCARD.getTitle(false);
        break;
      case 1:
        imageLoc = PopUp.IPHONE.getImage();
        iconLoc = PopUp.IPHONE.getIcon();
        text = PopUp.IPHONE.getTitle(false);
        break;
      case 2:
        imageLoc = PopUp.SUBSCRIBE.getImage();
        iconLoc = PopUp.SUBSCRIBE.getIcon();
        text = PopUp.SUBSCRIBE.getTitle(false);
        break;
      case 3:
        imageLoc = PopUp.TACOTOWN.getImage();
        iconLoc = PopUp.TACOTOWN.getIcon();
        text = PopUp.TACOTOWN.getTitle(false);
        break;
      case 4:
        imageLoc = PopUp.VIRUS.getImage();
        iconLoc = PopUp.VIRUS.getIcon();
        text = PopUp.VIRUS.getTitle(false);
        break;
      case 5:
        imageLoc = PopUp.VISITOR.getImage();
        iconLoc = PopUp.VISITOR.getIcon();
        text = PopUp.VISITOR.getTitle(false);
        break;
    }
    JPanel panel = generatePopup(text, imageLoc, iconLoc);
    JOptionPane.showMessageDialog(null, panel, "Congrats", JOptionPane.DEFAULT_OPTION);
  }
  
  private static JPanel generatePopup(String text, String imageLoc, String iconLoc) {
    ImageIcon winImage = new ImageIcon(imageLoc);
    JLabel label = new JLabel(text);
    JPanel textPanel = new JPanel();
    textPanel.add(label);
    JLabel imageLabel = new JLabel(winImage);
    JPanel imagePanel = new JPanel();
    imagePanel.add(imageLabel);
    JPanel panel = new JPanel(new BorderLayout());  
    panel.add(imagePanel, BorderLayout.CENTER);
    panel.add(textPanel, BorderLayout.SOUTH); 
    return panel;
  }
}
