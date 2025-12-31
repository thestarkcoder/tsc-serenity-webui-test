package starter.pages;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page object for the successful login page.
 * Contains Target definitions for elements on the success page.
 */
public final class LoggedInSuccessfullyPage {

    public static final Target SUCCESS_MESSAGE = Target.the("success message")
            .locatedBy("//strong[contains(text(),'Congratulations')] | //p[contains(text(),'successfully logged in')]");

    public static final Target LOGOUT_BUTTON = Target.the("logout button")
            .locatedBy("//a[text()='Log out']");

    private LoggedInSuccessfullyPage() {
        // Utility class - prevent instantiation
    }
}
