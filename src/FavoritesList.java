

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FavoritesList {

	/**
	 * Reads the favorites Json file to an array list.
	 */
	public static ArrayList<Media> readFavoritesList(InputStream in) {
	  ArrayList<Media> newList = new ArrayList<Media>();
	  if(in==null) {
	    Media defaultMedia = new Media("", "", "Add a Favorite", "");
	    newList.add(defaultMedia);
	    return newList;
	  } else {
	    return MovieJson.getMediaList(0, in);
	  }
	}

	/**
	 * Add a selected movie to the favoritesList and rewrite the file.
	 */
	public static void addFavorite(Media newFavorite, List<Media> favoritesList) {
		favoritesList.add(newFavorite);
		MovieJson.writeJson("src\\"+Model.PATH_FAVORITES, (ArrayList<Media>)favoritesList);
	}

	public static void removeFavorite(Media unfavorite, List<Media> favoritesList) {
		favoritesList.remove(unfavorite);
		MovieJson.writeJson("src\\"+Model.PATH_FAVORITES, (ArrayList<Media>)favoritesList);
	}
}
