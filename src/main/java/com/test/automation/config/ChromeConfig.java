package com.test.automation.config;

import java.util.logging.Level;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeConfig extends WebDriverConfig {

  static {
    System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
  }

  /**
   * Create a Chrome web-driver.
   * <p>browser is not headless</p>
   */
  public ChromeConfig() {
    this(false);
  }

  /**
   * Create a Chrome web-driver.
   * @param headless true if headless
   */
  public ChromeConfig(boolean headless) {
    driver = new ChromeDriver(getOptions(headless));
  }

  /**
   * Firefox driver options.
   * @param headless headless flag
   * @return options
   */
  private ChromeOptions getOptions(boolean headless) {
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
    logPrefs.enable(LogType.PROFILER, Level.INFO);
    logPrefs.enable(LogType.BROWSER, Level.INFO);
    logPrefs.enable(LogType.CLIENT, Level.INFO);
    logPrefs.enable(LogType.DRIVER, Level.INFO);
    logPrefs.enable(LogType.SERVER, Level.INFO);
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.setHeadless(headless);
    options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    return options;
  }
}
