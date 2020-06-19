package com.test.automation.config;

import java.util.logging.Level;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class FirefoxConfig extends WebDriverConfig {

  static {
    System.setProperty("webdriver.gecko.driver", "bin/geckodriver.exe");
  }

  /**
   * Create a Firefox web-driver.
   * <p>browser is not headless</p>
   */
  public FirefoxConfig() {
    this(false);
  }

  /**
   * Create a Firefox web-driver.
   * @param headless true if headless
   */
  public FirefoxConfig(boolean headless) {
    driver = new FirefoxDriver(getOptions(headless));
  }

  /**
   * Firefox driver options.
   * @return options
   */
  private FirefoxOptions getOptions(boolean headless) {
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
    logPrefs.enable(LogType.PROFILER, Level.INFO);
    logPrefs.enable(LogType.BROWSER, Level.INFO);
    logPrefs.enable(LogType.CLIENT, Level.INFO);
    logPrefs.enable(LogType.DRIVER, Level.INFO);
    logPrefs.enable(LogType.SERVER, Level.INFO);
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--start-maximized");
    options.setHeadless(headless);
    options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    return options;
  }
}
