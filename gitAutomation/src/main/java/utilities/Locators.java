/**
 * 
 */
package utilities;

/**
 * @link Locators
 * @description For handling all locators this class is used
 * @created Jul 8, 2020
 * @author yatendra.singh
 *
 */
public final class Locators {

  /**** login related xpaths ****/
  public static final String LOGIN_FIELD_ID = "login_field";
  public static final String PASSWORD_FIELD_ID = "password";
  public static final String LOGIN_BUTTON_XAPTH = "//input[@value='Sign in']";

  /****** locators for create repository ******/
  public static final String CREATEREPO_XPATH_DDP =
      "//summary[contains(@aria-label,'Create new…')]";
  public static final String NEW_REPOSITORY_OPTION = "//a[contains(text(),'New repository')]";
  public static final String VERIFY_REPO_NAME_XPATH =
      "//auto-check[@src='/repositories/check-name']//dl//dd[@class='error']";

  public static final String REPO_NAME_ID = "repository_name";
  public static final String CREATEREPO_BUTTON_XPATH =
      "//button[@type='submit' and contains(text(),'Create repository')]";

  /*** Navigate to Dashboard and search repository xpaths ***/
  public static final String NAVIGATE_TO_DASHBOARD_XAPTH =
      "(//header[@role='banner']//div//a[@class='Header-link'])[1]";
  public static final String SEARCH_REPOSITORY_NAME_XPATH =
      "//div[@role='navigation' and  @aria-label='Repositories']//div[@id='repos-container']//div[@role='search']"
          + "//input";

  /*** Create Issue/ comments/ title/id/body xpaths ***/
  public static final String CREATE_ISSUE_BUTTON_XPATH = "//span[@data-content='Issues']";
  public static final String NEW_ISSUE_BUTTON_XPATH = "//span[contains(text(),'New issue')]";
  public static final String SELECT_REPO_XPATH =
      "//ul[@data-filterable-for='dashboard-repos-filter-left']//li//a[@href=\"/replaceWithUserName/replaceWithRepoName\"]";;
  public static final String WRITE_ISSUE_TITLE_ID = "issue_title";
  public static final String ISSUE_BODY_ID = "issue_body";
  public static final String SUBMIT_NEW_ISSUE_BUTTON_XPATH =
      "(//button[contains(text(),'Submit new issue')])[1]";
  public static final String SELECT_ISSUE_FROM_SUGGESTION_XPATH =
      "//li[contains(@id,'suggester-issue')]";
  public static final String GET_ISSUE_NUMBER_CLASS = "gh-header-number";
  public static final String NEW_COMMENT_FIELD_ID = "new_comment_field";
  public static final String ADD_COMMENT_BUTTON_XPATH = "//button[contains(text(),'Comment')]";
  public static final String CLICK_ON_ISSUE = "//p//a[contains(text(),'replaceMe')]";

  /*** Delete Repository Xpaths ***/
  public static final String CLICK_SETTINGS_XAPTH =
      "//a[contains(@data-selected-links,'repo_settings')]";
  public static final String DELELE_REPO_BUTTON =
      "//summary[contains(text(),'Delete this repository')]";
  public static final String ENTER_ABOVE_VALUE_TO_CONFIRM_TEXTBOX =
      "//form[contains(@action,'/settings/delete')]//p//input";
  public static final String DELETE_THE_REPO_BUTTON =
      "//form[contains(@action,'/settings/delete')]//p/following::button[@type='submit']";

}
