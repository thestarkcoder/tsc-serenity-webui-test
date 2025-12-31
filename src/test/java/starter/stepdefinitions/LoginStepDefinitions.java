package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.actions.Login;
import starter.actions.NavigateTo;
import starter.pages.LoggedInSuccessfullyPage;
import starter.pages.LoginPage;
import starter.questions.TheCurrentUrl;
import starter.testdata.LoginTestData;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Step definitions for login functionality tests.
 * Uses Screenplay pattern with centralized page objects.
 */
public class LoginStepDefinitions {

    /**
     * Helper method to get the current actor.
     * Reduces code duplication across step methods.
     */
    private Actor theActor() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("a user is on the login page")
    public void aUserIsOnLoginPage() {
        OnStage.theActorCalled("user").wasAbleTo(NavigateTo.theLoginPage());
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        theActor().attemptsTo(
                Login.withCredentials(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD));
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithCredentials(String username, String password) {
        theActor().attemptsTo(Login.withCredentials(username, password));
    }

    @Then("the user should be redirected to the logged in successfully page")
    public void theUserShouldBeRedirected() {
        theActor().attemptsTo(
                WaitUntil.the(LoggedInSuccessfullyPage.SUCCESS_MESSAGE, isVisible())
                        .forNoMoreThan(10).seconds(),
                Ensure.that(TheCurrentUrl.value()).contains(LoginTestData.SUCCESS_URL_FRAGMENT));
    }

    @And("the user should see the success message")
    public void theUserShouldSeeSuccessMessage() {
        theActor().attemptsTo(
                Ensure.that(LoggedInSuccessfullyPage.SUCCESS_MESSAGE).isDisplayed());
    }

    @And("the user should see the logout button")
    public void theUserShouldSeeLogoutButton() {
        theActor().attemptsTo(
                Ensure.that(LoggedInSuccessfullyPage.LOGOUT_BUTTON).isDisplayed());
    }

    @Then("the user should see an error message displayed")
    public void theUserShouldSeeErrorMessage() {
        theActor().attemptsTo(
                WaitUntil.the(LoginPage.ERROR_MESSAGE, isVisible()).forNoMoreThan(10).seconds());
    }

    @And("the user should see the error message {string}")
    public void theUserShouldSeeErrorMessageText(String expectedMessage) {
        theActor().attemptsTo(
                Ensure.that(LoginPage.ERROR_MESSAGE).text().isEqualTo(expectedMessage));
    }
}
