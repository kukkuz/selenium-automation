package com.test.automation.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import com.test.automation.data.LoginPageData;
import com.test.automation.models.LoginCredentials;
import com.test.automation.suite.LoginTestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

public class LoginPageTest extends LoginTestSuite {

  private static final Logger logger = LoggerFactory.getLogger(LoginPageTest.class);
  private static final String BASE_URL = "http://localhost:8080/login";
  private static final String COOKIE_NAME = "myapibackend";

  @Test(
      priority = Integer.MIN_VALUE, // do this test first
      attributes = @CustomAttribute(name = "screenshot"),
      description = "Check if page loads"
  )
  public void testLoginPage() throws InterruptedException {
    logger.info("Check page load - wait for the initial redirect for the first time");
    script.openWindowWithUrl(BASE_URL)
        .waitForSeconds(5).waitForPageLoad();
  }

  @Test(
      priority = Integer.MAX_VALUE, // do this at the end
      dependsOnMethods = "testLoginPage",
      attributes = @CustomAttribute(name = "screenshot"),
      description = "Login positive test-case",
      dataProvider = "credentials", dataProviderClass = LoginPageData.class
  )
  public void loginToSiteIsSuccessLoadsDash(LoginCredentials credentials) {
    script.openWindowWithUrl(BASE_URL);
    logger.info("Trying to login for {}", credentials.getEmail());
    script.login(credentials)
        .waitForPageWithUrlRegex(
            "^http\\:\\/\\/localhost\\:8080\\/getting-started\\/\\d+$"
        );
    assertThat(script.getCookieWithName(COOKIE_NAME), is(not(nullValue())));
    logger.info("Successfully logged in to dashboard");
  }

  @Test(
      dependsOnMethods = "testLoginPage",
      description = "Login negative test-case - email valid but incorrect password",
      dataProvider = "invalid-password", dataProviderClass = LoginPageData.class
  )
  public void loginToSiteFailsOnInvalidPassword(LoginCredentials credentials) {
    script.openWindowWithUrl(BASE_URL);
    script.login(credentials).checkForErrorsOnLogin();
    assertThat(script.getCookieWithName(COOKIE_NAME), is(nullValue()));
  }

  @Test(
      dependsOnMethods = "testLoginPage",
      description = "Login negative test-case - email valid but password length < 6",
      dataProvider = "wrong-password-length", dataProviderClass = LoginPageData.class
  )
  public void loginToSiteFailsOnWrongPasswordLength(LoginCredentials credentials) {
    script.openWindowWithUrl(BASE_URL);
    script.login(credentials);
    assertThat(script.checkForValidationErrors(), is(true));
    assertThat(script.getCookieWithName(COOKIE_NAME), is(nullValue()));
  }

  @Test(
      dependsOnMethods = "testLoginPage",
      attributes = @CustomAttribute(name = "screenshot"),
      description = "Login negative test-case - invalid email",
      dataProvider = "invalid-email", dataProviderClass = LoginPageData.class
  )
  public void loginToSiteFailsOnInvalidEmail(LoginCredentials credentials) {
    script.openWindowWithUrl(BASE_URL);
    script.login(credentials);
    assertThat(script.checkForValidationErrors(), is(true));
    assertThat(script.getCookieWithName(COOKIE_NAME), is(nullValue()));
  }
}
