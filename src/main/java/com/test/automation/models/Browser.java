package com.test.automation.models;

import com.test.automation.config.ChromeConfig;
import com.test.automation.config.FirefoxConfig;
import com.test.automation.config.WebDriverConfig;
import java.util.Objects;
import java.util.function.Supplier;

public enum Browser {

  CHROME(ChromeConfig::new),
  FIREFOX(FirefoxConfig::new),
  CHROME_HEADLESS(() -> new ChromeConfig(true)),
  FIREFOX_HEADLESS(() -> new FirefoxConfig(true));

  private Supplier<WebDriverConfig> config;

  Browser(Supplier<WebDriverConfig> config) {
    this.config = config;
  }

  public Supplier<WebDriverConfig> getConfig() {
    return config;
  }

  /**
   * Get browser driver instance.
   * <p>
   *   Default is {@code CHROME} if {@code headless} is null. <br/>
   *   Default is {@code CHROME_HEADLESS} if {@code headless} is non-null.
   * </p>
   * @param browser browser name
   * @param headless true if headless browser
   * @return instance of browser
   */
  public static Browser of(String browser, String headless) {
    try {
      return valueOf(browser.toUpperCase()
          + (Objects.nonNull(headless) ? "_HEADLESS" : ""));
    } catch (IllegalArgumentException | NullPointerException e) {
      return Objects.nonNull(headless) ? CHROME_HEADLESS : CHROME; // default
    }
  }
}
