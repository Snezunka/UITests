Feature: Events Wikipedia articles with GEO points

@smoke
Scenario: Check number of articles with GEO points on Events Wikipedia page for today and tomorrow
    Given Open Wikipedia Today events page
    When Calculate number of articles with GEO points for Wikipedia Today events page
    And Open Wikipedia Tomorrow events page
    And Calculate number of articles with GEO points for Wikipedia Tomorrow events page
    Then Compare number of articles for today and tomorrow
