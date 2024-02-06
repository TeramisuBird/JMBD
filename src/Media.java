public class Media
{
  private final String id, // The ID of the movie. 
                    image, // A link an image to show for the movie.
                    title, // The title of the movie.
              description; // The description of the movie.
  
  public Media(String id, String image, String title, String description) {
    this.id = id;
    this.image = image;
    this.title = title;
    this.description = description;
  }
  
  public String toString() {
    String result = getTitle() + " " + getDescription();
    return result;
  }
  
  public String basicString() {
    String result = getTitle() + " " + getDescription();
    return result;
  }

  /**
   * @return the id
   */
  public final String getId()
  {
    return id;
  }
  
  /**
   * @return the image
   */
  public final String getImage()
  {
    return image;
  }
  
  /**
   * @return the title
   */
  public final String getTitle()
  {
    return title;
  }
  
  /**
   * @return the description
   */
  public final String getDescription()
  {
    return description;
  }
}
