package com.test.automation.scripts;

import com.test.automation.models.LoginCredentials;
import com.test.automation.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageScript extends BaseScript {

  private static final Logger logger = LoggerFactory.getLogger(LoginPageScript.class);

  /**
   * Init page elements.
   */
  public LoginPageScript() {
    initPageElements(LoginPage.class);
  }

  /**
   * Login Action.
   * <p>Enter email, password and submit login page</p>
   * @return instance of this class
   */
  public LoginPageScript login(LoginCredentials credentials) {
    logger.info("Adding login details to the login form");
    actionBuilder.moveToElement(LoginPage.emailField).click()
        .sendKeys(credentials.getEmail()).build().perform();
    actionBuilder.moveToElement(LoginPage.passwordField).click()
        .sendKeys(credentials.getPassword()).build().perform();
    logger.info("Submitting the login form");
    actionBuilder.moveToElement(LoginPage.submitBtn).click().build().perform();
    return this;
  }

  /**
   * Check for validation errors on login.
   * <p>Check if the error block is visible</p>
   */
  public void checkForErrorsOnLogin() {
    checkIfElementIsVisible(LoginPage.errorElement);
  }

  /**
   * Check for form validation errors.
   * <p>Check if the login button is disabled</p>
   * @return true if there are validation errors
   */
  public boolean checkForValidationErrors() {
    var attribute = LoginPage.submitBtn.getAttribute("aria-disabled");
    return attribute != null && attribute.equalsIgnoreCase("true");
  }
}
