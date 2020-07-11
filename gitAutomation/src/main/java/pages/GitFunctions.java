
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.BaseEventHandler;
import utilities.ConstantsAndValues;
import utilities.Locators;

/**
 * @link FunctionsToDo
 * @description All Git Functions for challenge are defined here
 * @created Jul 8, 2020
 * @author yatenda.singh
 *
 */
public class GitFunctions extends BaseEventHandler {

  /**
   * Description: Challenge #1:​ Repository Creation
   * 
   * @param enterRepositoryName : you want to create
   * @return
   */
  public boolean createRepository(String enterRepositoryName) {
    boolean status = false;
    mouseHoverAndClick(Locators.CREATEREPO_XPATH_DDP);
    clickElement("xpath", Locators.NEW_REPOSITORY_OPTION);
    enterText("id", Locators.REPO_NAME_ID, enterRepositoryName);
    try {
      String statusMessage = getText("xpath", Locators.VERIFY_REPO_NAME_XPATH);
      if (statusMessage.contains("already exists on this account")) {
        System.out.println(
            "This Repositiry name is already exist, Kindly Change the name of Repository and try again!");
        return status;
      }
      return status;
    } catch (Exception e) {
      WebElement element = driver.findElement(By.xpath(Locators.CREATEREPO_BUTTON_XPATH));
      try {
        if (element.isEnabled()) {
          System.out.println("Create Repository button is enabled and clicked!");
          moveToElementAndClick(element);
          System.out.println("Repository is cretaed");
          return status = true;
        }
      } catch (InterruptedException e1) {
        System.out
            .println("Unable to create Repository because Create repository button is disabled");
        return status;
      }
      return status;

    }
  }

  /**
   * Description: To create dynamic xpath
   */
  public String makeHrefXpath(String xpath, String replaceUser, String replaceWithUserName,
      String replaceRepo, String replaceWithRepoName) {
    xpath = xpath.replace(replaceUser, replaceWithUserName);
    xpath = xpath.replace(replaceRepo, replaceWithRepoName);
    return xpath;
  }

  public boolean NavigateToDashboardAndSearchRepoName(String userName, String repoNameToSearch) {
    try {
      // clickElement("xpath", Locators.NAVIGATE_TO_DASHBOARD_XAPTH);
      WebElement naviagteToDasboard = findElement("xpath", Locators.NAVIGATE_TO_DASHBOARD_XAPTH);
      moveToElementAndClick(naviagteToDasboard);
      enterText("xpath", Locators.SEARCH_REPOSITORY_NAME_XPATH, repoNameToSearch);
      String dynamicXpath = makeHrefXpath(Locators.SELECT_REPO_XPATH, "replaceWithUserName",
          userName, "replaceWithRepoName", repoNameToSearch);
      WebElement checkElement = findElement("xpath", dynamicXpath);
      if (checkElement.isDisplayed()) {
        return clickElement("xpath", dynamicXpath);
      } else {
        System.out.println("Repository Name does not exist!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Element not present or Repo does not exist");
      return false;
    }
  }

  /**
   * Description: Method to click on submit button to create issue
   * 
   * @return
   */
  public boolean issueCreationSubmitButton() {
    WebElement submitButton = driver.findElement(By.xpath(Locators.SUBMIT_NEW_ISSUE_BUTTON_XPATH));
    try {
      moveToElementAndClick(submitButton);
      System.out.println("Issue created successfully.");
      return true;
    } catch (InterruptedException e) {
      System.out.println("Element is not visible!");
      return false;
    }
  }

  /**
   * Description: To enter issue title, issue body and click on submit button
   * 
   * @param issueTitle : Name you want to give
   * @param issueBody : Text you want to write
   * @return
   */
  public boolean enterIssueDetailsAndSubmitIssue(String issueTitle, String issueBody) {
    if (!ConstantsAndValues.ISSUE.ISSUE_TITLE.isEmpty()) {
      enterText("id", Locators.WRITE_ISSUE_TITLE_ID, issueTitle);
      enterText("id", Locators.ISSUE_BODY_ID, issueBody);
      issueCreationSubmitButton();
      return true;
    }
    return false;
  }

  public void clickOnCreateAndNewIssueButton() {
    clickElement("xpath", Locators.CREATE_ISSUE_BUTTON_XPATH);
    clickElement("xpath", Locators.NEW_ISSUE_BUTTON_XPATH);
  }

  /**
   * Description : Challenge #2:​ Issue Creation ( Create an issue on Git hub​ in the repository
   * created previously)
   * 
   * @param issueTitle : Issue Title you want to enter
   * @param issueBody : Issue Body you want to enter
   * @return
   */
  public boolean issueCreation(String issueTitle, String issueBody) {

    waitTime("pageLoad", 2000);
    clickOnCreateAndNewIssueButton();
    try {
      enterIssueDetailsAndSubmitIssue(issueTitle, issueBody);
      String issueID = getIssueID();
      writeProperty("IssueNumber", issueID, "writedata.properties");
      return true;
    } catch (Exception e) {
      System.out.println("Please enter title first!");
      return false;
    }
  }

  /**
   * Description: Method for get Issue ID of created Issue (used in Challenge 2 and 4)
   * 
   * @return
   */
  public String getIssueID() {
    String issueID = getText("className", Locators.GET_ISSUE_NUMBER_CLASS);
    System.out.println("Created issue id is : " + issueID);
    return issueID;
  }

  /**
   * Description: Common method for enter Title and body of issue with issue id
   * 
   * @param textToEnter : Enter text
   * @param locator : Locator (xpath,id,class)
   * @param issueID : Enter issue id
   */
  public void enterNewIssueTitleOrBody(String textToEnter, String locator, String issueID) {

    WebElement element = findElement("id", locator);
    element.sendKeys(textToEnter);
    System.out.println("Entered detail is: " + textToEnter);
    element.sendKeys(issueID);
  }

  /**
   * Description : Challenge #2:​ (2) Create another issue on Git hub while mentioning previous
   * issue in description and title
   * 
   * @return
   */
  public boolean createNewIssueAndEnterIssueIDinDescription(String issueTitle, String issueBody) {
    boolean status = false;
    String issueID = getIssueID();
    clickOnCreateAndNewIssueButton();
    enterNewIssueTitleOrBody(issueTitle, Locators.WRITE_ISSUE_TITLE_ID, "");
    enterNewIssueTitleOrBody(issueBody, Locators.ISSUE_BODY_ID, issueID);
    System.out.println("Mentioning previous issue with id");
    WebElement suggestion =
        driver.findElement(By.xpath(Locators.SELECT_ISSUE_FROM_SUGGESTION_XPATH));
    if (suggestion.isDisplayed()) {
      suggestion.click();
      System.out.println("Issue mentioned!");
      issueCreationSubmitButton();
      waitTime("sleep", 4000);
      return status = true;
    }
    return status;
  }

  /**
   * Description: Challenge #3:​ Comment to an Issue 1. Add some comments to the issue created in
   * challenge #2
   * 
   * @param enterCommentToAdd
   * @return
   */
  public boolean addNewComment(String enterCommentToAdd) {
    try {
      enterText("id", Locators.NEW_COMMENT_FIELD_ID, enterCommentToAdd);
      clickElement("xpath", Locators.ADD_COMMENT_BUTTON_XPATH);
      return true;
    } catch (Exception e) {
      System.out.println("Unable to find Field for adding comment!");
      return false;
    }
  }

  /**
   * Description:Challenge #3:​ Comment to an Issue 2. Add emoji in the repository created in
   * challenge #1
   * 
   * @param enterEmojiValueToAdd : with defined code
   * @return
   */
  public boolean addEmojiInRepo(String enterEmojiValueToAdd) {
    try {
      enterText("id", Locators.NEW_COMMENT_FIELD_ID, enterEmojiValueToAdd);
      clickElement("xpath", Locators.ADD_COMMENT_BUTTON_XPATH);
      System.out.println("Emoji is added successfully ");
      waitTime("sleep", 5000);
      return true;
    } catch (Exception e) {
      System.out.println("Unable to find Field or emoji value is incorrect!");
      return false;
    }
  }

  /**
   * challenge 4:​ Issue mention in comments link to Issue 1. Create a new comment and mention any
   * of the previous issue (from challenge #2) 2. Navigate to the issue from the comment Issue
   * 
   */
  public boolean newCommentAndMentionPerivousIssueThenNavigateFromCommentIssue(
      String enterNewComment) {
    boolean status = false;
    try {
      String issueIdFromChallenge2 = readFromProperty("IssueNumber", "writedata.properties");
      System.out.println("Issuenumber added in challenge 2 is :" + issueIdFromChallenge2);
      // enterText("id", Locators.NEW_COMMENT_FIELD_ID, enterNewComment);
      enterNewIssueTitleOrBody(enterNewComment, Locators.NEW_COMMENT_FIELD_ID,
          issueIdFromChallenge2);
      clickElement("xpath", Locators.ADD_COMMENT_BUTTON_XPATH);
      String newValue = issueIdFromChallenge2.replaceAll("[^0-9]", " ");
      String newXpath = Locators.CLICK_ON_ISSUE.replace("replaceMe", newValue.trim());
      clickElement("xpath", newXpath);
      if (getText("className", Locators.GET_ISSUE_NUMBER_CLASS).equals(issueIdFromChallenge2)) {
        return status = true;
      } else {
        System.out
            .println("Both issue number aren't same, so re chcek the navigation through link!");

        return status;
      }
    } catch (Exception e) {
      System.out.println("Unable to find Field or emoji value is incorrect!");
      return status;
    }
  }

  /**
   * Challenge 5: Delete the repository
   */
  public boolean deleteRepository(String userName, String repoNameToDelete) {
    boolean status = false;
    clickElement("xpath", Locators.CLICK_SETTINGS_XAPTH);
    WebElement deleteRepoButton = findElement("xpath", Locators.DELELE_REPO_BUTTON);
    try {
      moveToElementAndClick(deleteRepoButton);
      String value = userName + "/" + repoNameToDelete;
      enterText("xpath", Locators.ENTER_ABOVE_VALUE_TO_CONFIRM_TEXTBOX, value);
      clickElement("xpath", Locators.DELETE_THE_REPO_BUTTON);
      System.out.println("Repository deleted successfully.");
      return status = true;
    } catch (InterruptedException e) {
      System.out.println("Unable to find Delete Repo element!");
      return status;
    }

  }

}
