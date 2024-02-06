import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MatchingGui {
  private JFrame frame;
  private JPanel panel;
  private JPanel resetPanel;
  private Card[] cards;
  private int width;
  private int height;
  private boolean playable;
  private Card match1;
  private Card match2;
  private int score;
  private int winScore;

  public MatchingGui(List<Media> movies) {
    // Create Frame and instance variables
    frame = new JFrame();
    panel = new JPanel();
    resetPanel = new JPanel();
    width = movies.size();
    height = 2;
    playable = true;
    match1 = null;
    match2 = null;
    score = 0;
    winScore = 0;

    // Create the card list
    populateArray(movies);

    // Create panel specifics
    panel.setPreferredSize(new Dimension(1000, 300));
    panel.setLayout(new GridLayout(height, width));
    resetPanel.setLayout(new GridLayout(3, 3));
    if (playable) {
      fillGrid();
    }

    // Create reset button
    JButton button = new JButton("Reset Game");
    button.setFont(new Font("Arial", Font.PLAIN, 40));

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (playable) {
          reset();
        }
      }
    });

    for (int i = 0; i < 4; i++) {
      resetPanel.add(new JPanel());
    }

    resetPanel.add(button);

    for (int i = 0; i < 4; i++) {
      resetPanel.add(new JPanel());
    }

    // Add it all together
    frame.setLayout(new GridLayout(2, 1));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
    frame.add(panel);
    frame.add(resetPanel);
    frame.pack();
    frame.setVisible(true);
  }

  public void reset() {
    setCards();
    match1 = null;
    match2 = null;
    score = 0;

    for (int i = 0; i < cards.length; i++) {
      if (cards[i] == null) {
        continue;
      }

      cards[i].setVisible(true);
    }
  }

  private void setCards() {
    panel.removeAll();
    fillGrid();
  }

  private void populateArray(List<Media> movies) {
    // Check if cards are possible to be made
    if (movies.size() == 1 && movies.get(0).getTitle().equals("Use search bar")) {
      JLabel errorLab = new JLabel("You need to search once to play");
      panel.add(errorLab);
      playable = false;
      return;
    } else if (movies.size() <= 10) {
      this.cards = new Card[movies.size() * 2];
    } else {
      this.cards = new Card[20];
    }

    int index = 0;
    int index2 = 0;
    winScore = cards.length;

    // Populate cards list
    while (index < cards.length) {
      cards[index] = new Card(movies.get(index2).getTitle(), movies.get(index2).getImage());
      JLabel lab = new JLabel(cards[index].getData());
      cards[index].add(lab);

      // Create title panel
      Card titleCard = cards[index];
      JButton button = new JButton("Press This");
      button.addActionListener(new ActionListener() {
        Card card = titleCard;

        public void actionPerformed(ActionEvent e) {
          if (match1 == null) {
            match1 = card;
            card.setBorder(BorderFactory.createLineBorder(Color.black));
          } else if (match2 == null) {
            match2 = card;
            card.setBorder(BorderFactory.createLineBorder(Color.black));
            checkMatch(match1, match2);
          }
        }
      });

      titleCard.add(button);

      index++;

      cards[index] = new Card(movies.get(index2).getImage(), movies.get(index2).getTitle());
      JLabel imageLabel = new JLabel();
      lab.setMinimumSize(new Dimension(100, 100));
      ImageIcon icon = resizeImage(imageLabel, cards[index]);

      if (icon != null) {
        imageLabel.setIcon(icon);
      } else {
        index++;
        index2++;
        continue;
      }

      // Create image panel
      cards[index].add(imageLabel);
      Card posterCard = cards[index];
      button = new JButton("Press This");
      button.addActionListener(new ActionListener() {
        Card card = posterCard;

        public void actionPerformed(ActionEvent e) {
          if (match1 == null) {
            match1 = card;
            card.setBorder(BorderFactory.createLineBorder(Color.black));
          } else if (match2 == null) {
            match2 = card;
            card.setBorder(BorderFactory.createLineBorder(Color.black));
            checkMatch(match1, match2);
          }
        }
      });

      cards[index].add(button);

      index++;
      index2++;
    }

    // Shuffle list
    List<Card> list = Arrays.asList(cards);
    Collections.shuffle(list);
    list.toArray(cards);
  }

  // Method just resizes images
  private ImageIcon resizeImage(JLabel lab, Card card) {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new URL(card.getData()));
    } catch (IOException e) {
      for (int i = 0; i < cards.length; i++) {
        if (card.equals(cards[i])) {
          cards[i] = null;
          cards[i - 1] = null;
          winScore -= 2;
          return null;
        }
      }
    }

    Image dimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);
    return imageIcon;
  }

  // Method fills the panel with the cards
  private void fillGrid() {
    for (int i = 0; i < cards.length; i++) {
      if (cards[i] == null) {
        continue;
      }

      panel.add(cards[i]);
    }
  }

  // Check the cards and if they match
  public void checkMatch(Card match1, Card match2) {
    if (match1.checkMatch(match2)) {
      this.match1.setVisible(false);
      this.match2.setVisible(false);
      infoBox("You got it right, bro", "Right");

      score += 2;

      // If the cards match, and you have matched all cards,
      // you win and info box is displayed
      if (score == winScore) {
        infoBox(
            "You have matched every box and have won the game! \n Press the reset button to play again",
            "Game Over");
      }
    } else {
      infoBox("You got it wrong, bro", "Wrong");
    }

    this.match1.setBorder(new EmptyBorder(0, 0, 0, 0));
    this.match2.setBorder(new EmptyBorder(0, 0, 0, 0));
    this.match1 = null;
    this.match2 = null;
  }

  public static void infoBox(String infoMessage, String title) {
    JOptionPane.showMessageDialog(null, infoMessage, title, JOptionPane.INFORMATION_MESSAGE);
  }
}
