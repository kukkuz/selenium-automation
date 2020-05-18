# selenium-automation

This is a reference implementation of web automation functional testing with Selenium &amp; TestNG

## What is included?

This project has a few functional tests written for a website's login page. You can reuse the project after rewriting the TestNG functional tests. You can find the following tools used in here:

1. Selenium tool for web automation (Chrome & Firefox supported)
2. TestNG as the testing tool with YAML config file
3. Assertions with Hamcrest
4. Logback as logging framework
5. Extent Reports as reporting tool
6. Checkstyle for code-formatting & style checks

## Getting Started

After rewriting the TestNG functional tests for you use case, you can run:

```shell script
gradle clean build
```

to run the functional tests in Chrome. There are some options available too:

| Option     | Allowed Values               |
|------------|:----------------------------:|
| browser    | chrome (default) / firefox   |
| headless   | (true if specified)          |

To test in a *headless* browser with firefox, you can use:

```shell script
gradle clean build -Dbrowser=firefox -Dheadless
```
