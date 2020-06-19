package com.test.automation.suite;

import com.test.automation.scripts.BaseScript;
import com.test.automation.scripts.LoginPageScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class LoginTestSuite implements BaseSuite {

  private static final Logger logger = LoggerFactory.getLogger(LoginTestSuite.class);

  protected static LoginPageScript script;

  /**
   * Initialize driver & page elements.
   */
  @BeforeSuite
  public void setUp() {
    logger.info("Starting up...");
    script = new LoginPageScript();
  }

  /**
   * Tear down resource.
   * @throws InterruptedException thread timeout interrupts
   */
  @AfterSuite
  public void tearDown() throws InterruptedException {
    logger.info("Shutting down...");
    script.waitForSeconds(2); // wait for manual check
    script.closeWindow();
  }

  /**
   * Get Script object.
   * @return selenium script
   */
  @Override
  public BaseScript getScript() {
    return script;
  }
}
