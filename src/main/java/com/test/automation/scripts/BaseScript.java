package com.test.automation.scripts;

import com.test.automation.config.WebDriverConfig;
import com.test.automation.models.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseScript {

  private static final Logger logger = LoggerFactory.getLogger(BaseScript.class);

  private WebDriverConfig config;

  protected WebDriverWait wait;

  protected WebDriverWait shortWait;

  protected Actions actionBuilder;

  /**
   * Init drivers and common utilities.
   */
  public BaseScript() {
    Browser browser = Browser.of(
        System.getProperty("browser"),
        System.getProperty("headless")
    );
    logger.info("======= Launching {} for automation tests =======", browser);
    config = browser.getConfig().get();
    wait = new WebDriverWait(getDriver(), 10);
    shortWait = new WebDriverWait(getDriver(), 1);
    actionBuilder = new Actions(getDriver());
  }

  /**
   * Get web driver.
   * @return selenium web driver
   */
  public WebDriver getDriver() {
    return config.getDriver();
  }

  /**
   * Load a URL in a window.
   * @param url URL
   * @return instance of this class
   */
  public BaseScript openWindowWithUrl(String url) {
    logger.info("Opening window with page {}", url);
    getDriver().navigate().to(url);
    return this;
  }

  /**
   * Close the browser window.
   */
  public void closeWindow() {
    logger.info("Closing window");
    getDriver().close();
  }

  /**
   * Init page object models.
   * @param className model class
   */
  public <T> void initPageElements(Class<T> className) {
    PageFactory.initElements(getDriver(), className);
  }

  /**
   * Wait for a page change.
   * <ul>
   *   <li>Wait for current page to go stale</li>
   *   <li>Wait for new page to finish loading</li>
   * </ul>
   * @return instance of this class
   */
  public BaseScript waitForPageChange() {
    wait.until(ExpectedConditions.stalenessOf(getDriver()
        .findElement(By.tagName("html"))));
    wait.until(driver -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState").equals("complete"));
    return this;
  }

  /**
   * Wait for a page to finish loading.
   * <p>Wait for new page to finish loading</p>
   * @return instance of this class
   */
  public BaseScript waitForPageLoad() {
    wait.until(driver -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState").equals("complete"));
    return this;
  }

  /**
   * Wait for a location to match the regex.
   * @param regex url regex pattern
   * @return instance of this class
   */
  public BaseScript waitForPageWithUrlRegex(String regex) {
    wait.until(ExpectedConditions.urlMatches(regex));
    return this;
  }

  /**
   * Get cookie with given name.
   * @param name cookie name
   * @return return cookie
   */
  public Cookie getCookieWithName(String name) {
    return getDriver().manage().getCookieNamed(name);
  }

  /**
   * Wait for the given time (in seconds).
   * @param seconds seconds to wait
   * @return instance of this class
   */
  public BaseScript waitForSeconds(long seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000);
    return this;
  }

  /**
   * Check if element is visible.
   * @param element web element
   */
  public void checkIfElementIsVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }
}

