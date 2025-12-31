package starter.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import starter.utils.CredentialsManager;

/**
 * Screenplay Task for navigation actions.
 */
public final class NavigateTo {

    /**
     * Navigate to the login page.
     * 
     * @return Performable task for navigation
     */
    public static Performable theLoginPage() {
        return Task.where("{0} opens the login page",
                Open.url(CredentialsManager.getAppUrl()));
    }

    private NavigateTo() {
        // Utility class - prevent instantiation
    }
}
