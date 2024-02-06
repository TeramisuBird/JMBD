import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieJson
{

  public static final String PATH_BASICS = "basic-search.json", PATH_MOVIES = "movie-search.json",
      PATH_SERIES = "series-search.json", PATH_ACTORS = "actor-search.json";
  
  public static String id = "", image = "", title = "", description = "", runtimeStr = "",
      genres = "", contentRating = "", stars = "";

  private static final String MY_KEY = "k_mcx0w8kk";

  public static ArrayList<Media> getMediaOnline(int selectedFilter, String searchKey, String filter)
  {
    HttpURLConnection connection = null;
    ArrayList<Media> mediaList = null;
    URL url = null;
    try
    {
      url = buildSearchURL(selectedFilter, searchKey, filter);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoInput(true);
      InputStream in = connection.getInputStream();
      mediaList = getMediaList(selectedFilter, in);
      writeMediaList(selectedFilter, mediaList);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return mediaList;
  }

  public static URL buildSearchURL(int selectedFilter, String searchKey, String filter)
  {
    URL url = null;
    Key key = new Secret_Key();
    try
    {
      switch (selectedFilter)
      {
        case 0:
          url = new URL("https://imdb-api.com/API/AdvancedSearch/" + key.getAPIKey() + "?title=" + searchKey
              + "&title_type=feature,tv_movie,tv_series" + ((filter.equals("")) ? "" : filter));
          break;
        case 1:
          url = new URL("https://imdb-api.com/API/AdvancedSearch/" + key.getAPIKey() + "?title=" + searchKey
              + "&title_type=feature,tv_movie" + ((filter.equals("")) ? "" : filter));
          break;
        case 2:
          url = new URL("https://imdb-api.com/API/AdvancedSearch/" + key.getAPIKey() + "?title=" + searchKey
              + "&title_type=tv_series" + ((filter.equals("")) ? "" : filter));
          break;
        case 3:
          url = new URL("https://imdb-api.com/en/API/SearchName/" + MY_KEY + "/" + searchKey);
          break;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return url;
  }
  
  public static InputStream readJson(String path) {
    return Main.class.getResourceAsStream(path);
  }
  
  public static void writeJson(String path, ArrayList<Media> movieList)
  {
    ObjectMapper mapper = new ObjectMapper();
    try
    {
      mapper.writeValue(new File(path), movieList);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void writeMediaList(int selectedFilter, ArrayList<Media> mediaList)
  {
    switch (selectedFilter)
    {
      case 0:
        writeJson("src\\" + PATH_BASICS, mediaList); break;
      case 1:
        writeJson("src\\" + PATH_MOVIES, mediaList); break;
      case 2:
        writeJson("src\\" + PATH_SERIES, mediaList); break;
      case 3:
        writeJson("src\\" + PATH_ACTORS, mediaList); break;
      default:
        break;
    }
  }

  /**
   * Gets & reads a JSON file containing all offline movie data.
   * 
   * @param path
   * @param selectedFilter
   * @return
   */
  public static ArrayList<Media> getMediaOffline(int selectedFilter) {
    switch (selectedFilter) {
      case 0:
        return getMediaList(0, readJson(PATH_BASICS));
      case 1:
        return getMediaList(1, readJson(PATH_MOVIES));
      case 2:
        return getMediaList(2, readJson(PATH_SERIES));
      case 3:
        return getMediaList(3, readJson(PATH_ACTORS));
      default:
        return new ArrayList<Media>();
    }
  }

  public static ArrayList<Media> getMediaList(int selectedFilter, InputStream in)
  {
    ArrayList<Media> mediaList = new ArrayList<Media>();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = null;
    Media newMedia = null;
    try
    {
      tree = mapper.readTree(in);
      tree = (tree.has("results")) ? tree.get("results") : tree;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    for (int i = 0; i < tree.size(); i++)
    {
      JsonNode node = tree.get(i);
      newMedia = readMedia(selectedFilter, node);
      mediaList.add(newMedia);
    }
    return mediaList;
  }

  public static Media readMedia(int selectedFilter, JsonNode node)
  {
    Media newMedia = null;
    switch (selectedFilter)
    {
      case 0:
        newMedia = readBasic(node); break;
      case 1:
        newMedia = readMovieList(node); break;
      case 2:
        newMedia = readSeriesList(node); break;
      case 3:
        newMedia = readActorList(node); break;
      default:
        break;
    }
    return newMedia;
  }

  public static Media readBasic(JsonNode node)
  {
    Media newMedia = null;
    id = node.get("id").asText();
    image = node.get("image").asText();
    title = node.get("title").asText();
    description = node.get("description").asText();
    newMedia = new Media(id, image, title, description);
    return newMedia;
  }

  public static Media readMovieList(JsonNode node)
  {
    Media newMedia = null;
    try
    {
      id = node.get("id").asText();
      image = node.get("image").asText();
      title = node.get("title").asText();
      description = node.get("description").asText();
      runtimeStr = node.get("runtimeStr").asText();
      genres = node.get("genres").asText();
      contentRating = node.get("contentRating").asText();
      stars = node.get("stars").asText();
    }
    catch (Exception e)
    {
      newMedia = new Movie(id, image, title, description, runtimeStr, genres, contentRating, stars);
    }
    newMedia = new Movie(id, image, title, description, runtimeStr, genres, contentRating, stars);
    return newMedia;
  }

  public static Media readSeriesList(JsonNode node)
  {
    Media newMedia = null;
    try
    {
      id = node.get("id").asText();
      image = node.get("image").asText();
      title = node.get("title").asText();
      description = node.get("description").asText();
      genres = node.get("genres").asText();
      contentRating = node.get("contentRating").asText();
      stars = node.get("stars").asText();
    }
    catch (Exception e)
    {
      newMedia = new Series(id, image, title, description, genres, contentRating, stars);
    }
    newMedia = new Series(id, image, title, description, genres, contentRating, stars);
    return newMedia;
  }

  public static Media readActorList(JsonNode node)
  {
    Media newMedia = null;
    id = node.get("id").asText();
    image = node.get("image").asText();
    title = node.get("title").asText();
    description = node.get("description").asText();
    newMedia = new Media(id, image, title, description);
    return newMedia;
  }
}