package com.test.automation.data;

import com.test.automation.models.LoginCredentials;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class LoginPageData {

  /**
   * Email-password test data.
   * @return iterator to the test data
   */
  @DataProvider(name = "credentials")
  public Iterator<Object> getLoginCredentials() {
    List<Object> list = new ArrayList<>();
    list.add(new LoginCredentials("example@domain.com", "passw@rd"));
    return list.iterator();
  }

  /**
   * Email-password test data with invalid password.
   * @return iterator to the test data
   */
  @DataProvider(name = "invalid-password")
  public Iterator<Object> getEmailAndInvalidPassword() {
    List<Object> list = new ArrayList<>();
    list.add(new LoginCredentials("example@domain.com",
        RandomStringUtils.randomAlphanumeric(10)));
    return list.iterator();
  }

  /**
   * Email-password test data with password length < 6.
   * @return iterator to the test data
   */
  @DataProvider(name = "wrong-password-length")
  public Iterator<Object> getEmailAndPasswordWithLengthLessThanSix() {
    List<Object> list = new ArrayList<>();
    list.add(new LoginCredentials("example@domain.com",
        RandomStringUtils.randomAlphanumeric(5)));
    return list.iterator();
  }

  /**
   * Email-password test data with invalid email.
   * @return iterator to the test data
   */
  @DataProvider(name = "invalid-email")
  public Iterator<Object> getInvalidEmailAndValidPassword() {
    List<Object> list = new ArrayList<>();
    list.add(new LoginCredentials(RandomStringUtils.randomAlphanumeric(10),
        RandomStringUtils.randomAlphanumeric(10)));
    return list.iterator();
  }
}
