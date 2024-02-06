import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MovieJson_MovieListReaderTest
{
  String MovieSearchKey = "Morbius";
  String SeriesSearchKey = "Flash";
  String PersonSearchKey = "Steve Carell";
  String noFilter = "";
  String SimpleFilter = "&genres=action";
  ArrayList<Media> basicSearch = MovieJson.getMediaOnline(0, MovieSearchKey, noFilter);
  ArrayList<Media> basicMovieSearch = MovieJson.getMediaOnline(1, MovieSearchKey, noFilter);
  ArrayList<Media> basicSeriesSearch = MovieJson.getMediaOnline(2, SeriesSearchKey, noFilter);
  ArrayList<Media> peopleSearch = MovieJson.getMediaOnline(3, PersonSearchKey, null);
  ArrayList<Media> search = MovieJson.getMediaOnline(0, MovieSearchKey, SimpleFilter);
  ArrayList<Media> MovieSearch = MovieJson.getMediaOnline(1, MovieSearchKey, SimpleFilter);
  ArrayList<Media> SeriesSearch = MovieJson.getMediaOnline(2, SeriesSearchKey, SimpleFilter);
  
  @Test
  void SearchTest() {
    assertTrue("Morbius".equals(basicSearch.get(0).getTitle()));
    assertTrue("Morbius".equals(basicMovieSearch.get(0).getTitle()));
    assertTrue("The Flash".equals(basicSeriesSearch.get(0).getTitle()));
    assertTrue("Steve Carell".equals(peopleSearch.get(0).getTitle()));
  }
  
  @Test
  void FiltersTest() {
    assertTrue("Morbius".equals(search.get(0).getTitle()));
    assertTrue("Morbius".equals(MovieSearch.get(0).getTitle()));
    assertTrue("The Flash".equals(SeriesSearch.get(0).getTitle()));
  }

}
