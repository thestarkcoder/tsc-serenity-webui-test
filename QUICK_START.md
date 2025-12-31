# Quick Start Guide

## Practice Test Automation - Login Test Suite

Get started with this Serenity BDD test automation project in 5 minutes.

---

## Prerequisites

Ensure you have the following installed:

- **Java JDK 17 or higher**
  ```bash
  java -version
  ```

- **Maven 3.6+** (recommended) or Gradle 7+
  ```bash
  mvn -version
  ```

- **Chrome Browser** (default) or Firefox
  ```bash
  google-chrome --version
  ```

---

## Quick Setup

### 1. Clone or Download the Project

```bash
cd /path/to/tsc-serenity-webui-test
```

### 2. Verify Configuration

Check that `credentials.properties` exists:
```pwsh
Get-Content src/test/resources/credentials.properties
```

Should contain:
```properties
valid.username=student
valid.password=Password123
app.url=https://practicetestautomation.com/practice-test-login/
```

`app.url` is used by `NavigateTo.theLoginPage()` via `CredentialsManager.getAppUrl()` (no hardcoded URL in code).

---

## Running Tests

### Option 1: Run All Tests (Recommended)

```bash
mvn clean verify
```

**What happens**:
1. Cleans previous build artifacts
2. Compiles test code
3. Runs all 5 test scenarios in parallel
4. Generates Serenity reports

**Expected output**:
```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Option 2: Run Specific Test Tags

**Smoke tests only**:
```bash
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

**Positive tests only**:
```bash
mvn clean verify -Dcucumber.filter.tags="@positive"
```

**Negative tests only**:
```bash
mvn clean verify -Dcucumber.filter.tags="@negative"
```

### Option 3: Run in Different Browser

**Firefox**:
```bash
mvn clean verify -Ddriver=firefox
```

**Chrome (default)**:
```bash
mvn clean verify -Ddriver=chrome
```

### Option 4: Debug Mode (Browser Visible)

```bash
mvn clean verify -Dheadless.mode=false
```

---

## Viewing Test Reports

### After Test Execution

1. **Full Interactive Report**:
   ```bash
   # Windows
   start target/site/serenity/index.html
   
   # Mac
   open target/site/serenity/index.html
   
   # Linux
   xdg-open target/site/serenity/index.html
   ```

2. **Single Page Summary**:
   ```bash
   open target/site/serenity/serenity-summary.html
   ```

### Report Contents

- Test execution results with screenshots
- Living documentation from feature files
- Test coverage by feature and tag
- Detailed step-by-step execution logs
- Failure analysis with stack traces
- Performance metrics

---

## Test Scenarios

### Positive Tests
- **Successful Login** - Valid credentials lead to successful authentication

### Negative Tests
- **Invalid Username** - Error shown for incorrect username
- **Invalid Password** - Error shown for incorrect password
- **Empty Username** - Error shown when username is blank
- **Empty Password** - Error shown when password is blank

---

## Project Structure Quick Reference

```
tsc-serenity-webui-test/
├── src/test/
│   ├── java/starter/
│   │   ├── actions/              # Screenplay Tasks
│   │   │   ├── Login.java
│   │   │   └── NavigateTo.java
│   │   ├── pages/                # Page Target Definitions
│   │   │   ├── LoginPage.java
│   │   │   └── LoggedInSuccessfullyPage.java
│   │   ├── questions/            # Screenplay Questions
│   │   │   └── TheCurrentUrl.java
│   │   ├── stepdefinitions/      # Cucumber Glue Code
│   │   │   ├── LoginStepDefinitions.java
│   │   │   └── ParameterDefinitions.java
│   │   ├── testdata/             # Test Data Constants
│   │   │   └── LoginTestData.java
│   │   ├── utils/                # Helper Classes
│   │   │   └── CredentialsManager.java
│   │   └── CucumberTestSuite.java
│   └── resources/
│       ├── features/login/
│       │   └── login.feature     # BDD Test Scenarios
│       ├── credentials.properties # Test Credentials
│       ├── serenity.conf         # Serenity Config
│       └── logback-test.xml      # Logging Config
├── pom.xml                       # Maven Build File
├── build.gradle                  # Gradle Build File
└── README.md                     # Full Documentation
```

---

## Common Issues & Solutions

### Issue: Chrome driver not found

**Solution**: Serenity automatically downloads WebDriver binaries via WebDriverManager. Ensure you have internet connectivity.

### Issue: Tests timeout

**Solutions**:
- Check network connectivity to https://practicetestautomation.com
- Increase timeout in `LoginStepDefinitions.java`
- Run in headed mode to observe: `mvn clean verify -Dheadless.mode=false`

### Issue: Build fails with compilation errors

**Solutions**:
```bash
# Clean and rebuild
mvn clean compile

# Update dependencies
mvn dependency:purge-local-repository
```

### Issue: Reports not generated

**Solution**: Use `mvn verify` instead of `mvn test`:
```bash
mvn clean verify
```

### Issue: Cannot see browser during test execution

**Solution**: Disable headless mode:
```bash
mvn clean verify -Dheadless.mode=false
```

---

## IDE Setup

### IntelliJ IDEA

1. **Import Project**:
   - File → Open → Select `pom.xml`
   - Choose "Open as Project"

2. **Install Plugins**:
   - Cucumber for Java
   - Gherkin

3. **Run Tests from IDE**:
   - Right-click on `CucumberTestSuite.java`
   - Select "Run 'CucumberTestSuite'"

4. **Run Single Feature**:
   - Right-click on `login.feature`
   - Select "Run Feature: login"

### Eclipse

1. **Import Project**:
   - File → Import → Maven → Existing Maven Projects
   - Browse to project directory

2. **Install Plugins**:
   - Cucumber Eclipse Plugin
   - TestNG or JUnit

3. **Run Tests**:
   - Right-click on `pom.xml`
   - Run As → Maven test

### VS Code

1. **Install Extensions**:
   - Extension Pack for Java
   - Cucumber (Gherkin) Full Support
   - Test Runner for Java

2. **Run Tests**:
   - Open terminal in VS Code
   - Run: `mvn clean verify`

---

## Configuration Files

### credentials.properties
```properties
# Update these values for different environments
valid.username=student
valid.password=Password123
app.url=https://practicetestautomation.com/practice-test-login/
```

### serenity.conf
```hocon
# Browser configuration
webdriver {
  driver = chrome  # Change to 'firefox' if needed
}
headless.mode = false  # Set to true for CI/CD

# Screenshot settings
serenity {
    take.screenshots = FOR_FAILURES
    restart.browser.for.each = feature
}
```

---

## Next Steps

### For Developers

1. **Add New Tests**:
   - Add scenarios to `login.feature`
   - Implement step definitions in `LoginStepDefinitions.java`
   - Run tests to verify

2. **Extend Framework**:
   - Add new page objects in `pages/`
   - Create new actions in `actions/`
   - Add questions in `questions/`

3. **Read Full Documentation**:
   - See `README.md` for detailed information
   - Check `AGENT.md` for development history

### For Testers

1. **Understand BDD Scenarios**:
   - Open `login.feature`
   - Read feature descriptions
   - Understand Given-When-Then structure

2. **Run Tests Locally**:
   - Follow "Running Tests" section above
   - View generated reports

3. **Report Issues**:
   - Document failed scenarios
   - Attach Serenity reports
   - Include console logs

### For CI/CD Integration

1. **Jenkins Pipeline**:
   ```groovy
   stage('Test') {
       steps {
           sh 'mvn clean verify'
       }
       post {
           always {
               publishHTML([
                   reportDir: 'target/site/serenity',
                   reportFiles: 'index.html',
                   reportName: 'Serenity Report'
               ])
           }
       }
   }
   ```

2. **GitLab CI**:
   ```yaml
   test:
     script:
       - mvn clean verify
     artifacts:
       paths:
         - target/site/serenity
       reports:
         junit: target/failsafe-reports/TEST-*.xml
   ```

3. **GitHub Actions**:
   ```yaml
   - name: Run Tests
     run: mvn clean verify
   
   - name: Publish Report
     uses: actions/upload-artifact@v2
     with:
       name: serenity-report
       path: target/site/serenity
   ```

---

## Support

### Documentation
- **README.md** - Comprehensive project documentation
- **MIGRATION_SUMMARY.md** - Change history

### Resources
- [Serenity BDD Documentation](https://serenity-bdd.github.io/theserenitybook/latest/index.html)
- [Cucumber Documentation](https://cucumber.io/docs)
- [Screenplay Pattern Guide](https://serenity-bdd.info/docs/screenplay/screenplay_fundamentals.html)

### Contact
- Raise issues in project repository
- Check existing documentation first

---

## Quick Commands Cheat Sheet

```bash
# Basic test run
mvn clean verify

# Smoke tests only
mvn clean verify -Dcucumber.filter.tags="@smoke"

# Debug mode (browser visible)
mvn clean verify -Dheadless.mode=false

# Firefox browser
mvn clean verify -Ddriver=firefox

# View report (Mac/Linux)
open target/site/serenity/index.html

# View report (Windows)
start target/site/serenity/index.html

# Compile only
mvn clean compile

# Clean build artifacts
mvn clean
```

---

**Get Started Now**: Run `mvn clean verify` and view your first report!
