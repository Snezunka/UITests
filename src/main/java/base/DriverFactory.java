package base;

import config.DriverTimeouts;
import config.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

public class DriverFactory {
    protected static WebDriver driver;
    protected static WebDriverWait driverWaiter;

    public WebDriver createDriver() {
        if (driver == null) {
            String browserName = Utils.getBrowserName();
            if ("firefox".equals(browserName)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browserName)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("edge".equals(browserName)) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if ("safari".equals(browserName)) {
                throw new UnsupportedOperationException("Sorry, currently tests don't support safari browser");
            } else throw new IllegalArgumentException("You entered incorrect browser name. Please check.");
        }
        driverWaiter = new WebDriverWait(driver, DriverTimeouts.MEDIUM_TIMEOUT.getSeconds());
        driver.manage().window().maximize();
        return driver;
    }

    public void tearDown() {
        driver.quit();
        driver = null;
    }

    protected void initElements(DriverFactory page) {
        PageFactory.initElements(driver, page);
    }
}
