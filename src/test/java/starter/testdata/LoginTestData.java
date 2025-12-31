package starter.testdata;

import starter.utils.CredentialsManager;

public final class LoginTestData {
    
    // Valid credentials - loaded from credentials.properties
    public static final String VALID_USERNAME = CredentialsManager.getValidUsername();
    public static final String VALID_PASSWORD = CredentialsManager.getValidPassword();
    
    // Expected messages
    public static final String INVALID_USERNAME_ERROR = "Your username is invalid!";
    public static final String INVALID_PASSWORD_ERROR = "Your password is invalid!";
    
    // Expected URL fragment
    public static final String SUCCESS_URL_FRAGMENT = "logged-in-successfully";
    
    private LoginTestData() {
        // Utility class - prevent instantiation
    }
}
