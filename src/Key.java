
/**
 * At least 1 class must implement this interface with a proper key. 
 * 
 * @author Joseph Blethen
 * @version 1.0
 *
 */
public interface Key {
  
  /**
   * Retrieves the custom API key for this particular instance.
   * 
   * @return A String of an API key.
   */
  public String getAPIKey();

}
