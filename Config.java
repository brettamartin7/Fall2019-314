// This class is for all our configuration data. By putting it all in one place, we can easily make changes to the program without having to hunt down where in the code a constant is defined.

public class Config {
  public static String CROSS_PATH = ""; // If you don't know what the static keyword does, you better go look it up now.
  public static String PRIME_PATH = "";
  public static String APPLICATION_NAME = "";
  public static final String DATA_PATH = System.getProperty("user.dir") + "/data/";	//relative to folder code is ran from
}