package starter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class CredentialsManager {
    
    private static final String CREDENTIALS_FILE = "credentials.properties";
    private static final Properties properties = new Properties();
    
    static {
        try (InputStream input = CredentialsManager.class
                .getClassLoader()
                .getResourceAsStream(CREDENTIALS_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + CREDENTIALS_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load credentials", ex);
        }
    }
    
    public static String getValidUsername() {
        return getProperty("valid.username");
    }
    
    public static String getValidPassword() {
        return getProperty("valid.password");
    }
    
    public static String getAppUrl() {
        return getProperty("app.url");
    }
    
    private static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Property '" + key + "' not found in " + CREDENTIALS_FILE);
        }
        return value.trim();
    }
    
    private CredentialsManager() {
        // Utility class - prevent instantiation
    }
}
