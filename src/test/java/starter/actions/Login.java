package starter.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.pages.LoginPage;

/**
 * Screenplay Task for performing login action.
 */
public final class Login {

        /**
         * Creates a login task with the provided credentials.
         * 
         * @param username the username to enter
         * @param password the password to enter
         * @return Performable task for login
         */
        public static Performable withCredentials(String username, String password) {
                return Task.where("{0} logs in with provided credentials",
                        Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                        Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                        Click.on(LoginPage.SUBMIT_BUTTON));
        }

        private Login() {
                // Utility class - prevent instantiation
        }
}
