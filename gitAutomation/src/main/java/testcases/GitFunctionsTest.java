/**
 * 
 */
package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GitFunctions;
import pages.LoginPage;
import utilities.ConstantsAndValues;

/**
 * @link LoginPageTest
 * @description
 * @created Jul 8, 2020
 * @author yatendra.singh
 *
 */
public class GitFunctionsTest extends TestBase {

  protected LoginPage loginPage;
  protected GitFunctions gitFunctions;

  @BeforeTest(alwaysRun = true)
  public void init() {
    loginPage = new LoginPage();
    gitFunctions = new GitFunctions();
  }

  @Test(enabled = true)
  public void verifyLogInUserTest() {
    System.out.println("TC: Log in with user test started");
    Assert.assertTrue(loginPage.login());
  }

  @Test(enabled = true, dependsOnMethods = {"verifyLogInUserTest"}, priority = 1)
  public void verifyCreateRepositoryTest() {
    System.out.println("TC: create Repository test started");
    gitFunctions.createRepository(ConstantsAndValues.REPOSITORYCREATION.REPOSITORY_NAME);
  }

  @Test(enabled = true, dependsOnMethods = {"verifyCreateRepositoryTest"}, priority = 2)
  public void VerifyIssueCreationInExistingTest() {
    System.out.println("TC: Issue Creation in existing Repository test started");
    gitFunctions.NavigateToDashboardAndSearchRepoName(ConstantsAndValues.LOGINDETAILS.USERNAME,
        ConstantsAndValues.SEARCHREPO.REPO_NAME_TO_SEARCH);
    Assert.assertTrue(gitFunctions.issueCreation(ConstantsAndValues.ISSUE.ISSUE_TITLE,
        ConstantsAndValues.ISSUE.ISSUE_BODY));
  }

  @Test(enabled = true, dependsOnMethods = {"VerifyIssueCreationInExistingTest"}, priority = 3)
  public void verifyCreateNewIssueAndEnterIssueIDinDescription() {
    System.out.println(
        "TC: create new Issue while mentioning previous issue in description test started");
    Assert.assertTrue(gitFunctions.createNewIssueAndEnterIssueIDinDescription(
        ConstantsAndValues.ISSUE.NEW_ISSUE_TITLE, ConstantsAndValues.ISSUE.NEW_ISSUE_BODY));
  }

  @Test(enabled = true, dependsOnMethods = {"verifyCreateNewIssueAndEnterIssueIDinDescription"},
      priority = 4)
  public void verifyAddNewCommentTest() {
    System.out.println("TC: Comment to an issue test started");
    Assert.assertTrue(gitFunctions.addNewComment(ConstantsAndValues.ISSUE.ADD_NEW_COMMENT));
  }

  @Test(enabled = true, dependsOnMethods = {"verifyAddNewCommentTest"}, priority = 4)
  public void verifyAddEmojiTest() {
    System.out.println("TC: Add emoji test started");
    Assert.assertTrue(gitFunctions.addEmojiInRepo(ConstantsAndValues.ISSUE.ADD_EMOJI_VALUE));
  }

  @Test(enabled = true, dependsOnMethods = {"verifyAddEmojiTest"}, priority = 5)
  public void verifyLinkIssueTest() {
    System.out.println(
        "TC: Link issue by mentioning in comments and click and navigate to issue test started");
    gitFunctions.newCommentAndMentionPerivousIssueThenNavigateFromCommentIssue(
        ConstantsAndValues.ISSUE.ADD_NEW_COMMENT);
  }

  @Test(enabled = true, dependsOnMethods = {"verifyLogInUserTest"}, priority = 6)
  public void verifyDeleteRepositoryTest() {
    System.out.println("TC: Delete repository test started");
    Assert.assertTrue(
        gitFunctions.NavigateToDashboardAndSearchRepoName(ConstantsAndValues.LOGINDETAILS.USERNAME,
            ConstantsAndValues.SEARCHREPO.REPO_NAME_TO_SEARCH));
    Assert.assertTrue(gitFunctions.deleteRepository(ConstantsAndValues.LOGINDETAILS.USERNAME,
        ConstantsAndValues.REPOSITORYCREATION.REPOSITORY_NAME));
    Assert.assertFalse(
        gitFunctions.NavigateToDashboardAndSearchRepoName(ConstantsAndValues.LOGINDETAILS.USERNAME,
            ConstantsAndValues.SEARCHREPO.REPO_NAME_TO_SEARCH));
  }

}
