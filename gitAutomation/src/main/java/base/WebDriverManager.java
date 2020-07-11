/**
 * 
 */
package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.ConstantsAndValues;

/**
 * @link WebDriverManager
 * @description : Driver initialization class
 * @created Jul 8, 2020
 * @author yatendra.Singh
 *
 */
public final class WebDriverManager {

  public WebDriver driver;

  private static WebDriverManager webDriverManager = new WebDriverManager();

  public static WebDriverManager getInstance() {
    return webDriverManager;
  }

  private WebDriverManager() {

  }

  private WebDriver initializeDriver() {
    String path = System.getProperty("user.dir");
    if (this.driver == null) {
      try {
        try {
          System.out.println("In to initializeDriver method");
          String driverExecutable = null;
          String browserName = ConstantsAndValues.DRIVERDETAILS.BROWSERNAME;
          System.out.println("Browser invoked :" + browserName);

          if (browserName.equals("chrome")) {
            driverExecutable = ConstantsAndValues.DRIVERDETAILS.DRIVER_EXECUTABLE;
            System.setProperty(driverExecutable,
                path + ConstantsAndValues.DRIVERDETAILS.DRIVER_LOCATION);
            driver = new ChromeDriver();
            driver.get(ConstantsAndValues.LOGINDETAILS.SITE_URL);
            driver = new ChromeDriver();
            System.out.println("Driver Initialized");
            DesiredCapabilities capability = new DesiredCapabilities();
            capability = DesiredCapabilities.chrome();
            capability.setCapability("ignoreZoomSetting", true);
            capability.setCapability("ignoreProtectedModeSettings", true);
            capability.setPlatform(Platform.ANY);

          } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
            // firefox code can be implemented here
          } else if (browserName.equals("IE")) {
            // IE code can be implemented here
          }

          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
          driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
          driver.manage().window().maximize();
          System.out.println("Browser maximized");
        } catch (NullPointerException e) {
          return driver;
        } catch (Exception e) {
          return driver;
        }
        return driver;
      } catch (NullPointerException e) {
        System.out.println("initializeDriver is null");
        return driver;
      } catch (Exception e) {
        System.out.println("initializeDriver Issue");
        return driver;
      }
    }
    return this.driver;

  }

  public WebDriver getDriver() {
    if (this.driver == null)
      return initializeDriver();
    else
      return driver;
  }

}
