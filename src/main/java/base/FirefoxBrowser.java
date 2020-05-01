package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements Browser {

    public WebDriver open() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
