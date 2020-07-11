package testcases;

import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import base.BaseEventHandler;
import base.WebDriverManager;
import utilities.ConstantsAndValues;

/**
 * @link TestBase
 * @description : To start before and after suite
 * @created Jul 8, 2020
 * @author yatendra.Singh
 *
 */

public class TestBase extends BaseEventHandler {

  @BeforeSuite(alwaysRun = true)
  public void BasePageNavigation() throws IOException {
    System.out.println("Before suite started");
    driver = WebDriverManager.getInstance().getDriver();
    driver.get(ConstantsAndValues.LOGINDETAILS.SITE_URL);
  }

  @AfterSuite(alwaysRun = true)
  public void teardown() {
    if (driver != null)
      driver.quit();

  }
}
