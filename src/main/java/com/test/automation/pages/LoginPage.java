package com.test.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

  @FindBy(css = "form input[type=email]")
  public static WebElement emailField;

  @FindBy(css = "form input[type=password]")
  public static WebElement passwordField;

  @FindBy(css = "form button[type=submit]")
  public static WebElement submitBtn;

  @FindBy(css = "form div[data-error-type=INVALID_PASSWORD]")
  public static WebElement errorElement;
}
