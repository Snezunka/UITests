package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Utils {

  protected static Properties getProperties(String fileName) {
    Properties properties = new Properties();
    String filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileName;
    try {
      InputStream inputStream = new FileInputStream(filePath);
      properties.load(inputStream);
    } catch (IOException io) {
      throw new IllegalArgumentException("Incorrect file path " + filePath);
    }
    return properties;
  }

}
