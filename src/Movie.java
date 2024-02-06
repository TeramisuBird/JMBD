
public class Movie extends Media
{
  private final String runTime, // movie runtime
      genres, // movie genres
      contentRating, // movie rating
      stars; // movie major stars

  public Movie(String id, String image, String title, String description, String runTime,
      String genres, String contentRating, String stars)
  {
    super(id, image, title, description);
    this.runTime = runTime;
    this.contentRating = contentRating;
    this.genres = genres;
    this.stars = stars;
  }

  // return runtime.
  public String getRuntime()
  {
    if (!this.runTime.equals("null"))
    {
      return this.runTime;
    }
    return "N/A";
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
    String result2 = getRuntime() + " " + getRating() + " " + getGenres() + " \n";
    String result3 = getStars();
    return result + result2 + result3;
  }
}
