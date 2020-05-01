package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser {
    public WebDriver open() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
