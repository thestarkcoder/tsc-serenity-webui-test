package starter.pages;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page object for the Login page.
 * Contains Target definitions for login form elements.
 */
public final class LoginPage {

    public static final Target USERNAME_FIELD = Target.the("username field")
            .locatedBy("#username");

    public static final Target PASSWORD_FIELD = Target.the("password field")
            .locatedBy("#password");

    public static final Target SUBMIT_BUTTON = Target.the("submit button")
            .locatedBy("#submit");

    public static final Target ERROR_MESSAGE = Target.the("error message")
            .locatedBy("#error");

    private LoginPage() {
        // Utility class - prevent instantiation
    }
}
