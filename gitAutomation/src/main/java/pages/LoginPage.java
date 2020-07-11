
package pages;

import base.BaseEventHandler;
import utilities.ConstantsAndValues;
import utilities.Locators;

/**
 * @link loginPage
 * @description : login in method mentioned here
 * @created Jul 8, 2020
 * @author yatendra.singh
 *
 */
public class LoginPage extends BaseEventHandler {

  public boolean login() {
    enterText("id", Locators.LOGIN_FIELD_ID, ConstantsAndValues.LOGINDETAILS.USERID);
    enterText("id", Locators.PASSWORD_FIELD_ID, ConstantsAndValues.LOGINDETAILS.PASSWORD);
    return clickElement("xpath", Locators.LOGIN_BUTTON_XAPTH);
  }
}
