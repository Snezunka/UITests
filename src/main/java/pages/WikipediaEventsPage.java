package pages;

import base.DriverFactory;
import config.DriverTimeouts;
import config.BrowserUtils;
import enums.Locations;
import helpers.DateHelper;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class WikipediaEventsPage extends DriverFactory {
    private DateHelper dateHelper = new DateHelper();

    public WikipediaEventsPage() {
        initElements(this);
    }

    @FindBy(xpath = "//table//tr[@class='navbox-title']/th/a")
    private WebElement currentMonth;

    @FindBy(xpath = "//table//tr[@class='navbox-title']/td[text()='>>']")
    private WebElement nextMonthLink;

    @FindBy(id = "firstHeading")
    private WebElement pageHeader;

    public void openWikipediaTodayEventsPage() {

        String todayDate = dateHelper.getTodayDate();
        String url = BrowserUtils.getBaseUrl() + todayDate;
        driver.get(url);
    }

    public void openWikipediaTomorrowEventsPage() {
        int firstDayOfMonth = 1;
        String tomorrowMonth = dateHelper.getTomorrowMonth();
        int tomorrowDay = dateHelper.getTomorrowDay();
        String tomorrowDate = tomorrowMonth + " " + tomorrowDay;
        if (tomorrowDay == firstDayOfMonth) {
            selectOnCalendarNextMonth();
            waitUntilMonthEquals(tomorrowMonth);
        }
        selectDayOnCalendar(tomorrowDay);
        waitUntilPageHeaderEquals(tomorrowDate);
    }

    private void waitUntilPageHeaderEquals(String tomorrowDate) {
        Awaitility.await()
                .atMost(DriverTimeouts.MEDIUM_TIMEOUT.getSeconds(), TimeUnit.SECONDS)
                .until(() -> pageHeader.getText().toLowerCase().contains(tomorrowDate));
    }

    private void selectDayOnCalendar(int tomorrowDay) {
        By calendarDayPath = getCalendarDayElementPath(tomorrowDay);
        driver.findElement(calendarDayPath).click();
    }

    private void waitUntilMonthEquals(String tomorrowMonth) {
        Awaitility.await()
                .atMost(DriverTimeouts.MEDIUM_TIMEOUT.getSeconds(), TimeUnit.SECONDS)
                .until(() -> currentMonth.getText().equals(tomorrowMonth));
    }

    private void selectOnCalendarNextMonth() {
        nextMonthLink.click();
    }

    public int getNumberOfArticlesWithGeoPoints() {
        final int[] numberOfArticlesWithGeoPoints = {0};
        Arrays.stream(Locations.values())
                .forEach(location -> {
                    if (isGEOLocationElementPresent(location.getName())) numberOfArticlesWithGeoPoints[0]++;
                });
        return numberOfArticlesWithGeoPoints[0];
    }

    private boolean isGEOLocationElementPresent(String locationName) {
        try {
            driver.findElement(getGeoLocationElementPath(locationName));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private By getGeoLocationElementPath(String locationName) {
        return new By.ByXPath("//ul/li/a[text()='" + locationName + "']");
    }

    private By getCalendarDayElementPath(int text) {
        return new By.ByXPath("//table/tbody//td/a[text()='" + text + "']");
    }
}
