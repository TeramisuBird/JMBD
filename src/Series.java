
public class Series extends Media
{
  private final String genres, // series genres
      contentRating, // series rating
      stars; // series major stars

  public Series(String id, String image, String title, String description, String genres,
      String contentRating, String stars)
  {
    super(id, image, title, description);
    this.contentRating = contentRating;
    this.genres = genres;
    this.stars = stars;
  }

  // return genres.
  public String getGenres()
  {
    if (!this.genres.equals("null"))
    {
      return this.genres;
    }
    return "N/A";
  }

  // return rating.
  public String getRating()
  {
    if (!this.contentRating.equals("null"))
    {
      return this.contentRating;
    }
    return "N/A";
  }

  // return stars.
  public String getStars()
  {
    if (!this.stars.equals("null"))
    {
      return this.stars;
    }
    return "N/A";
  }

  public String toString()
  {
    String result = getTitle() + " " + getDescription() + " \n";
    String result2 = getRating() + " " + getGenres() + " \n";
    String result3 = getStars();
    return result + result2 + result3;
  }
}
