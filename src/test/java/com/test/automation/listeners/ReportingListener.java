package com.test.automation.listeners;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.CustomAttribute;

public interface ReportingListener {

  Logger logger = LoggerFactory.getLogger(ReportingListener.class);

  /**
   * Predicate to check if screenshot needed.
   * <p>Screenshot is taken on custom attribute condition</p>
   */
  Predicate<CustomAttribute> takeScreenshot =
      (CustomAttribute a) -> a.name().equals("screenshot");

  /**
   * Take screenshot using web driver.
   * @param driver web driver
   * @param attributes custom attributes of {@code @Test}
   * @return screenshot if created successfully
   */
  default Optional<File> saveScreenshot(
      WebDriver driver, CustomAttribute[] attributes) {
    return Arrays.stream(attributes).filter(takeScreenshot).map(v ->
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)).findAny();
  }

  /**
   * Create screenshot.
   * <p>created if instructed in custom-attribute)</p>
   * @param result test-result
   */
  void checkScreenshotOption(ITestResult result);
}
