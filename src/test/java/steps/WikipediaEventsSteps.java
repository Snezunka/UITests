package steps;

import contexts.ScenarioContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import pages.WikipediaEventsPage;

import static contexts.ScenarioContext.Context.NUMBER_OF_ARTICLES_WITH_GEO_POINT_TODAY;
import static contexts.ScenarioContext.Context.NUMBER_OF_ARTICLES_WITH_GEO_POINT_TOMORROW;

@Slf4j
public class WikipediaEventsSteps {
    private final ScenarioContext scenarioContext = new ScenarioContext();
    private final WikipediaEventsPage wikipediaEventsPage = new WikipediaEventsPage();

    @Given("Open Wikipedia Today events page")
    public void openWikipediaTodayEventsPage() {
        wikipediaEventsPage.openWikipediaTodayEventsPage();
    }

    @When("Open Wikipedia Tomorrow events page")
    public void openWikipediaTomorrowEventsPage() {
        wikipediaEventsPage.openWikipediaTomorrowEventsPage();
    }

    @When("Calculate number of articles with GEO points for Wikipedia Today events page")
    public void calculateNumberOfArticlesWithGeoPointsForWikipediaTodayEventsPage() {
        int numberOfArticles = wikipediaEventsPage.getNumberOfArticlesWithGeoPoints();
        scenarioContext.setContext(NUMBER_OF_ARTICLES_WITH_GEO_POINT_TODAY, numberOfArticles);
    }

    @When("Calculate number of articles with GEO points for Wikipedia Tomorrow events page")
    public void calculateNumberOfArticlesWithGeoPointsForWikipediaTomorrowEventsPage() {
        int numberOfArticles = wikipediaEventsPage.getNumberOfArticlesWithGeoPoints();
        scenarioContext.setContext(NUMBER_OF_ARTICLES_WITH_GEO_POINT_TOMORROW, numberOfArticles);
    }

    @Then("Compare number of articles for today and tomorrow")
    public void compareNumberOfArticlesForTodayAndTomorrow() {
        int numberOfArticlesToday = scenarioContext.getContext(NUMBER_OF_ARTICLES_WITH_GEO_POINT_TODAY);
        int numberOfArticlesTomorrow = scenarioContext.getContext(NUMBER_OF_ARTICLES_WITH_GEO_POINT_TOMORROW);
//        if (numberOfArticlesToday > numberOfArticlesTomorrow) {
////            log.info(String.format("Number of articles with GEO points for today %s is bigger than %s for tomorrow",
////                    numberOfArticlesToday, numberOfArticlesTomorrow));
////        } else if (numberOfArticlesToday < numberOfArticlesTomorrow) {
////            log.info(String.format("Number of articles with GEO points for today %s is less than %s for tomorrow",
////                    numberOfArticlesToday, numberOfArticlesTomorrow));
////        } else log.info(String.format("Number of articles with GEO points for today %s is equal to %s for tomorrow",
////                numberOfArticlesToday, numberOfArticlesTomorrow));
        Assertions.assertThat(numberOfArticlesToday)
            .as("Number of articles today should be equals number of articles tomorrow")
            .isEqualTo(numberOfArticlesTomorrow);
    }
}
