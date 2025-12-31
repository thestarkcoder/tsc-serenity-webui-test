# Practice Test Automation - Login Test Suite

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Test Coverage](https://img.shields.io/badge/tests-5%2F5%20passing-brightgreen)]()
[![Code Quality](https://img.shields.io/badge/code%20quality-A+-brightgreen)]()

Serenity BDD test automation project for the Practice Test Automation login functionality, following Screenplay pattern best practices.

## Project Overview

This project demonstrates automated testing of login functionality using:
- **Serenity BDD 4.3.2** - Powerful test reporting and living documentation
- **Cucumber 7.x** - Behavior-Driven Development framework
- **Screenplay Pattern** - Modern, maintainable test design pattern with Questions
- **Selenium WebDriver** - Browser automation with WebDriverManager
- **Maven** - Build tool (primary)

### Test Application
- **URL**: https://practicetestautomation.com/practice-test-login/
- **Config**: `src/test/resources/credentials.properties` provides `valid.username`, `valid.password`, and `app.url`

### Project Structure
```
src/test/
├── java/starter/
│   ├── actions/              # Screenplay Tasks
│   │   ├── NavigateTo.java
│   │   └── Login.java
│   ├── pages/                # Page Target Definitions
│   │   ├── LoginPage.java
│   │   └── LoggedInSuccessfullyPage.java
│   ├── questions/            # Screenplay Questions (NEW)
│   │   └── TheCurrentUrl.java
│   ├── stepdefinitions/      # Cucumber Glue Code
│   │   ├── LoginStepDefinitions.java
│   │   └── ParameterDefinitions.java
│   ├── testdata/             # Test Data Constants (NEW)
│   │   └── LoginTestData.java
│   ├── utils/                # Helper Classes (NEW)
│   │   └── CredentialsManager.java
│   └── CucumberTestSuite.java
└── resources/
    ├── features/login/
    │   └── login.feature     # BDD Test Scenarios
    ├── credentials.properties # External Credentials (NEW)
    ├── serenity.conf
    └── logback-test.xml
```

## Test Scenarios

The project includes comprehensive login test coverage:

### Positive Tests
1. **Successful Login** (@positive @smoke)
   - Valid credentials result in successful login
   - Verifies redirection to success page
   - Validates success message display
   - Confirms logout button presence

### Negative Tests (Data-Driven)
2. **Invalid Credentials** - Login fails with:
   - Invalid username
   - Invalid password
3. **Empty Credentials** - Login fails with:
   - Empty username
   - Empty password

**Total Test Coverage**: 5 scenarios (1 positive + 2 Scenario Outlines)

All negative tests verify appropriate error message display.


## Quick Start

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6+ (or use included Gradle wrapper)
- Chrome browser (default) or Firefox

### Running the Tests

#### Using Maven (Primary)
```bash
# Run all tests and generate reports
mvn clean verify

# Run tests with specific tags
mvn clean verify -Dcucumber.filter.tags="@positive"
mvn clean verify -Dcucumber.filter.tags="@smoke"
mvn clean verify -Dcucumber.filter.tags="@negative"

# Run in Firefox
mvn clean verify -Ddriver=firefox

# Run in different environment
mvn clean verify -Denvironment=staging
```

### Viewing Test Reports

After running tests, open the Serenity report:
```
target/site/serenity/index.html
```

The reports include:
- Test execution results with screenshots
- Living documentation
- Test coverage by feature
- Detailed step-by-step execution logs
- Failure analysis and diagnostics

## Implementation Details

### Screenplay Pattern Implementation

This project uses the Screenplay pattern, which describes tests in terms of actors and tasks they perform:
```java
@Given("a user is on the login page")
public void aUserIsOnLoginPage() {
  OnStage.theActorCalled("user").wasAbleTo(NavigateTo.theLoginPage());
}

@When("the user logs in with username {string} and password {string}")
public void theUserLogsInWithCredentials(String username, String password) {
  OnStage.theActorInTheSpotlight().attemptsTo(Login.withCredentials(username, password));
}

@Then("the user should be redirected to the logged in successfully page")
public void theUserShouldBeRedirected() {
  OnStage.theActorInTheSpotlight().attemptsTo(
    WaitUntil.the(LoggedInSuccessfullyPage.SUCCESS_MESSAGE, isVisible()).forNoMoreThan(10).seconds(),
    Ensure.that(TheCurrentUrl.value()).contains(LoginTestData.SUCCESS_URL_FRAGMENT)
  );
}
```

#### Navigation Task (src/test/java/starter/actions/NavigateTo.java)
```java
public final class NavigateTo {
    public static Performable theLoginPage() {
        return Task.where("{0} opens the login page",
        Open.url(CredentialsManager.getAppUrl()));
    }
}
```

#### Login Action (src/test/java/starter/actions/Login.java)
```java
public final class Login {
    public static Performable withCredentials(String username, String password) {
  return Task.where("{0} logs in with provided credentials",
    Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
    Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
    Click.on(LoginPage.SUBMIT_BUTTON)
  );
    }
}
```

#### Targets (Pages)
Page classes in `src/test/java/starter/pages/` are **Targets-only** (no methods).

```java
public final class LoginPage {
  public static final Target USERNAME_FIELD = Target.the("username field").locatedBy("#username");
  public static final Target PASSWORD_FIELD = Target.the("password field").locatedBy("#password");
  public static final Target SUBMIT_BUTTON = Target.the("submit button").locatedBy("#submit");
  public static final Target ERROR_MESSAGE = Target.the("error message").locatedBy("#error");
}
```

```java
public final class LoggedInSuccessfullyPage {
  public static final Target SUCCESS_MESSAGE = Target.the("success message")
    .locatedBy("//strong[contains(text(),'Congratulations')] | //p[contains(text(),'successfully logged in')]");
  public static final Target LOGOUT_BUTTON = Target.the("logout button").locatedBy("//a[text()='Log out']");
}
```

### Feature File Example

```gherkin
Feature: Login Functionality
  As a user of the Practice Test Automation website
  I want to be able to log in with my credentials
  So that I can access the protected content

  Background:
    Given a user is on the login page

  @positive @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with valid credentials
    Then the user should be redirected to the logged in successfully page
    And the user should see the success message
    And the user should see the logout button
```


## Configuration

### WebDriver Configuration

- Browser config lives in `src/test/resources/serenity.conf`.
- The application URL is read from `src/test/resources/credentials.properties` (`app.url`) via `CredentialsManager`.

## Test Execution Summary

Latest test run results:
- **Scenarios**: 5
- **Passed**: 5
- **Failed**: 0
- **Skipped**: 0
- **Success Rate**: 100%

Test tags for selective execution:
- `@positive` - Happy path tests
- `@negative` - Error condition tests
- `@smoke` - Critical functionality tests

## Project Dependencies

Key dependencies:
- Serenity BDD: 4.3.2
- Serenity Cucumber: 4.3.2
- Cucumber JUnit Platform Engine: 7.31.0
- JUnit Platform Suite: 1.13.0
- Selenium: aligned via BOM (see `pom.xml`)

## Best Practices Demonstrated

1. **Screenplay Pattern**: Task-based test design for better maintainability
2. **Actor Model**: Tests written from user perspective
3. **Page Objects**: Centralized element locators
4. **Explicit Waits**: Proper synchronization with `WaitUntil`
5. **BDD with Cucumber**: Living documentation
6. **Single Responsibility**: Small, focused classes
7. **Reusable Components**: Actions and tasks can be composed

## Troubleshooting

### Common Issues

**Chrome driver not found**
- Solution: Serenity automatically downloads drivers via WebDriverManager

**Tests timeout**
- Check network connectivity
- Increase wait timeouts in LoginStepDefinitions.java
- Disable headless mode for debugging

**Reports not generated**
- Ensure you run `mvn verify` (not `mvn test`)
- Check target/site/serenity directory exists

### Debug Mode

Run in headed mode (browser visible):
```bash
mvn clean verify -Dheadless.mode=false
```

## Want to Learn More?

For more information about Serenity BDD:
- [Serenity BDD Documentation](https://serenity-bdd.github.io/theserenitybook/latest/index.html)
- [Learn Serenity BDD Online](https://expansion.serenity-dojo.com/)
- [Serenity BDD Blog](https://johnfergusonsmart.com/category/serenity-bdd/)
- [Byte-sized Serenity BDD YouTube Channel](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)

## License

This project is licensed under the terms specified in the LICENSE file.
