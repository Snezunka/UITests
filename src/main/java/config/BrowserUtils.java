package config;

public class BrowserUtils extends Utils {

    public static String getBrowserName() {
        return getProperties("browser.properties").getProperty("browser.name");
    }

    public static String getBaseUrl() {
        return getProperties("browser.properties").getProperty("browser.base_url");
    }
}
