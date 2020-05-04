package config;

public class TestRailUtils extends Utils {

  public static String getTestRailEndPoint() {
    return getProperties("testRail.properties").getProperty("testRail.endPoint");
  }

  public static String getTestRailUser() {
    return getProperties("testRail.properties").getProperty("testRail.userName");
  }

  public static String getTestRailPassword() {
    return getProperties("testRail.properties").getProperty("testRail.password");
  }

  public static int getTestRailProjectId() {
    return Integer.parseInt(getProperties("testRail.properties").getProperty("testRail.projectId"));
  }

  public static boolean getTestRailRunCreation() {
    return Boolean.parseBoolean(getProperties("testRail.properties").getProperty("testRail.runCreation"));
  }

  public static int getTestRailSuiteId() {
    return Integer.parseInt(getProperties("testRail.properties").getProperty("testRail.suiteId"));
  }

  public static int getTestRailMilestoneId() {
    return Integer.parseInt(getProperties("testRail.properties").getProperty("testRail.milestoneId"));
  }
}
