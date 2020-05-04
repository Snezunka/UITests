package steps;

import base.DriverFactory;
import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Case;
import com.codepine.api.testrail.model.CaseField;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Suite;
import config.TestRailUtils;
import cucumber.api.Result.Type;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

  private static int myProjectId;
  private static int testsRunId;
  private static TestRail testRail;
  private static Project myProject;
  private static Case testCase;
  private static Suite testSuite;
  private static List<CaseField> caseFields;
  private static List<ResultField> resultFields;
  private static boolean hasTestRailRunCreationAllowed;
  private DriverFactory driverFactory = new DriverFactory();

  static {
    myProjectId = TestRailUtils.getTestRailProjectId();
    hasTestRailRunCreationAllowed = TestRailUtils.getTestRailRunCreation();
    if (hasTestRailRunCreationAllowed) {
      testRail = TestRail
          .builder(TestRailUtils.getTestRailEndPoint(), TestRailUtils.getTestRailUser(),
              TestRailUtils.getTestRailPassword()).applicationName("playground").build();
      myProject = testRail.projects().get(myProjectId).execute();
      caseFields = testRail.caseFields().list().execute();
      resultFields = testRail.resultFields().list().execute();
      testSuite = testRail.suites().get(TestRailUtils.getTestRailSuiteId()).execute();
      createNewTestRun();
    }
  }

  @Before
  public void beforeScenario(Scenario scenario) {
    log.info("**********************************************");
    log.info("Start new test: " + scenario.getName());
    driverFactory.createDriver();
  }

  @After(order = 3)
  public void initCaseByCucumberScenarioTitle(Scenario scenario) {
    String scenarioName = scenario.getName();
    testCase = testRail.cases().list(myProject.getId(), testSuite.getId(), caseFields).execute()
        .stream()
        .filter(testCase -> testCase.getTitle().equals(scenarioName))
        .findFirst()
        .get();
  }

  @After(order = 2)
  public void setTestResultsInTestRail(Scenario scenario) {
    Type scenarioStatus = scenario.getStatus();
    testRail
        .results()
        .addForCase(testsRunId, testCase.getId(),
            new Result().setStatusId(getStatusMap().get(scenarioStatus)), resultFields)
        .execute();
  }

  @After(order = 1)
  public void afterAll() {
    driverFactory.tearDown();
  }

  private Map<Type, Integer> getStatusMap() {
    Map<Type, Integer> map = new HashMap<>();
    map.put(Type.PASSED, 1);
    map.put(Type.SKIPPED, 2);
    map.put(Type.PENDING, 3);
    map.put(Type.UNDEFINED, 4);
    map.put(Type.FAILED, 5);
    return map;
  }

  private static void createNewTestRun() {
    testsRunId = testRail
        .runs()
        .add(myProject.getId(), new Run()
            .setMilestoneId(TestRailUtils.getTestRailMilestoneId())
            .setName("AutomationRun " + new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date())))
        .execute()
        .getId();
  }
}
