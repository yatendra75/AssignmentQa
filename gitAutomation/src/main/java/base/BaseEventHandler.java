/**
 * 
 */
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import utilities.ConstantsAndValues;

/**
 * @link BaseEventHandler
 * @description : All event handlers are mentioned in this clasa
 * @created Jul 8, 2020
 * @author yatendra.singh
 *
 */
public class BaseEventHandler {

  public static WebDriver driver;
  WebElement element;

  /**
   * Description: Driver initializer
   */
  public static void initializeDriver() {
    String path = System.getProperty("user.dir");
    System.setProperty("webdriver.chrome.driver",
        path + ConstantsAndValues.DRIVERDETAILS.DRIVER_LOCATION);
    driver = new ChromeDriver();
    driver.get(ConstantsAndValues.LOGINDETAILS.SITE_URL);
  }

  /**
   * Description: To Find element by diffrent locators
   * 
   * @param type
   * @param locator
   */
  public WebElement findElement(String type, String locator) {
    try {
      switch (type) {
        case "id":
          element = driver.findElement(By.id(locator));
          break;
        case "xpath":
          // explicitWait(10, "presence", locator);
          element = driver.findElement(By.xpath(locator));
          break;
        case "name":
          element = driver.findElement(By.name(locator));
          break;
        case "className":
          element = driver.findElement(By.className(locator));
          break;
        default:
          element = null;
          break;
      }
      return element;
    } catch (NoSuchElementException | NullPointerException e) {
      System.out.println("findElement:" + e.getMessage());
      return null;
    }
  }

  /**
   * Description: This method will directly findElement clear the field and sendkeys
   * 
   * @param type
   * @param xpath
   * @param value
   */
  public boolean enterText(String type, String xpath, String value) {
    try {
      element = findElement(type, xpath);
      element.clear();
      element.sendKeys(value);
      System.out.println("Text is entered.");
      return true;
    } catch (Exception e) {
      System.out.println("enterText:" + e.getMessage());
      return false;
    }
  }

  /**
   * Description : To use sleep, implicitlyWait, pageLoadTimeout
   * 
   * @param waitType
   * @param time
   */
  public void waitTime(String waitType, int time) {
    try {
      switch (waitType) {
        case "implicit":
          driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
          break;
        case "sleep":
          Thread.sleep(time);
          break;
        case "pageLoad":
          driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
          break;
      }
    } catch (Exception e) {
      System.out.println("waitTime:" + e.getMessage());
    }
  }

  /**
   * Description: This method will directly findElement and perform click on it
   * 
   * @param type
   * @param locator
   */
  public boolean clickElement(String type, String locator) {
    try {
      findElement(type, locator).click();
      waitTime("sleep", 2000);
      System.out.println("Clicked the element!");
      return true;
    } catch (Exception e) {
      System.out.println("Unable to click Element:" + e.getMessage());
      return false;
    }
  }

  /**
   * Description: To move to webElement
   * 
   * @param element
   * @throws InterruptedException
   */
  public void moveToElementAndClick(WebElement element) throws InterruptedException {
    Actions actions = new Actions(driver);
    actions.moveToElement(element);
    actions.click().perform();
  }

  /**
   * Description mouse hover on to an element and then click the element
   * 
   * @param xpath
   */
  public void mouseHoverAndClick(String xpath) {
    try {
      Actions actions = new Actions(driver);
      WebElement target = driver.findElement(By.xpath(xpath));
      System.out.println("Mouse Hover actione performed on element");
      actions.moveToElement(target).click().perform();
    } catch (Exception e) {
      System.out.println("mouseHoverAndClick:" + e.getMessage());
    }
  }

  /**
   * 
   * Description: This method will directly findElement and get Text from the element
   * 
   * @param type
   * @param locator
   * @return
   */
  public String getText(String type, String locator) {
    waitTime("sleep", 2000);
    element = findElement(type, locator);
    return element.getText();
  }

  // read data from property file
  public String readFromProperty(String variable, String propertyFileName) {
    Properties props = new Properties();
    InputStream is = null;
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    // First try loading from the current directory
    try {
      File f = new File(path + propertyFileName);
      is = new FileInputStream(f);
    } catch (Exception e) {
      is = null;
    }
    try {
      if (is == null) {
        // Try loading from classpath
        is = getClass().getResourceAsStream(path + propertyFileName);
      }
      // Try loading properties from the file (if found)
      props.load(is);
    } catch (Exception e) {
      System.out.println("readFromProperty" + e.getMessage());
    }
    String value = props.getProperty(variable);
    if (is != null) {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return value;
  }

  // write data in property file
  public String writeProperty(String variable, String value, String propertyFileName) {
    Properties props = new Properties();
    OutputStream output = null;
    InputStream is = null;
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    try {
      is = new FileInputStream(path + propertyFileName);
      props.load(is);
      is.close();

      output = new FileOutputStream(path + propertyFileName);
      props.setProperty(variable, value);
      props.store(output, null);
      System.out.println("writeProperty:" + variable + " value: " + value);

    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
          if (variable.equalsIgnoreCase("IssueNumber")) {

          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }

}
