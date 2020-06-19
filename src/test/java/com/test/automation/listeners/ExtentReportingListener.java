package com.test.automation.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.test.automation.suite.BaseSuite;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

public class ExtentReportingListener extends ExtentITestListenerClassAdapter
    implements ReportingListener {

  @Override
  public synchronized void onTestSuccess(ITestResult result) {
    this.checkScreenshotOption(result);
    super.onTestSuccess(result);
  }

  @Override
  public synchronized void onTestFailure(ITestResult result) {
    this.checkScreenshotOption(result);
    super.onTestFailure(result);
  }

  @Override
  public synchronized void onTestSkipped(ITestResult result) {
    this.checkScreenshotOption(result);
    super.onTestSkipped(result);
  }

  @Override
  public void checkScreenshotOption(ITestResult result) {
    BaseSuite suite = (BaseSuite) result.getInstance();
    this.saveScreenshot(
        suite.getScript().getDriver(),
        result.getMethod().getAttributes()
    ).ifPresent(file -> {
      try {
        File snap = new File("build/reports/extent-report/" + file.getName());
        FileHandler.copy(file, snap);
        ExtentTestManager.getTest().info("", MediaEntityBuilder
            .createScreenCaptureFromPath(snap.getName()).build());
      } catch (IOException e) {
        ExtentTestManager.getTest().info("Error saving screenshot to extent-report :(");
      }
    });
  }
}
