package utilities;

/**
 * @link ConstantsAndValues
 * @description : All values like login credentials and urls will be dinfined here
 * @created Jul 8, 2020
 * @author yatendra.singh
 *
 */
public final class ConstantsAndValues {

  public static class LOGINDETAILS {

    public static final String SITE_URL = "https://github.com/login";
    public static final String USERID = "yatendra4singh@gmail.com";
    public static final String PASSWORD = "Test@1947";
    public static final String USERNAME = "yatendra75";
  }

  public static class DRIVERDETAILS {
    public static final String DRIVER_EXECUTABLE = "webdriver.chrome.driver";
    public static final String BROWSERNAME = "chrome";
    public static final String DRIVER_LOCATION =
        "\\src\\main\\resources\\drivers\\chromedriver.exe";

  }

  public static class REPOSITORYCREATION {
    public static final String REPOSITORY_NAME = "QA-Assignment";
  }

  public static class SEARCHREPO {
    public static final String REPO_NAME_TO_SEARCH = "QA-Assignment";
  }

  public static class ISSUE {
    public static final String ISSUE_TITLE = "Creating New Issue";
    public static final String ISSUE_BODY = "This is new issue";

    public static final String NEW_ISSUE_TITLE = "Creating another issue  ";
    public static final String NEW_ISSUE_BODY = "This is another issue  ";

    public static final String ADD_NEW_COMMENT = "New comment: What is the status of the issue.";
    public static final String ADD_EMOJI_VALUE = ":relaxed:";

  }
}
