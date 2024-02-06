import java.util.Random;

public enum PopUp
{

  IPHONE("FREE IPHONE GIVEAWAY!  Clik here","./resources/iPhone.jpg","./resources/apple/jpg"),
  GIFTCARD("Free Gift Card!  Go two our site","./resources/giftCard.jpg","./resources/money.jpg"),
  SUBSCRIBE("Do you want to subcribe for future notifications?","./resources/subscribe.jpg","./resource/thumb.jpg"),
  VISITOR("You are our 1,000,00th visitor!  Click here for your free gift!","./resources/1000000.jpg","./resources/visitor.jpg"),
  TACOTOWN("Visit Taco Town today ad get the Special!","./resources/tacoTownSpecial.jpg","./resources/tacoTownLogo.jpg"),
  VIRUS("You're computer has been infected with a virus!","./resources/VirusImage.jpg","./resources/virusIcon.jpg");
  
  private final String title;
  private final String image;
  private final String icon;
  private final long seed = 1241124432;
  private final Random random;
  
  PopUp(String title, String image, String icon) {
    this.title = title;
    this.image = image;
    this.icon = icon;
    this.random = new Random(this.seed);
  }

  public String getTitle(boolean funny)
  {
    if (funny) {
      int value = random.nextInt(100);
      String text = this.title;
      if (value % 7 == 0) {
        text = textFunnyizer(text);
      } else {
        text = this.title;
      }
      return text;
    }
    
    return this.title;
  }

  public String getImage()
  {
    return image;
  }

  public String getIcon()
  {
    return icon;
  }

  
  private String textFunnyizer(String text) {
    String result = "";
    text = text.toLowerCase();
    for (int i = 0; i < text.length(); i++) {
      if (i % 2 == 0) {
        String temp = text.substring(i, i + 1);
        temp = temp.toUpperCase();
        result += temp;
      } else {
        String temp = text.substring(i, i + 1);
        result += temp;
      }
    }
    return result;
  }
}
