package utils;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private static String propertiesFileName = "src/main/resources/testSendBox.properties";

    @Getter
    private static boolean localRun;
    @Getter
    private static boolean notificationEnabled;

    public TestProperties() {
        String propertyFileName = System.getenv("PROPERTY_FILE");
        if (propertyFileName != null) {
            propertiesFileName = propertyFileName;
        }
        try (FileInputStream fis = new FileInputStream(propertiesFileName)) {
            Properties property = new Properties();
            property.load(fis);

            localRun = Boolean.parseBoolean(getPropertyHandler(property, "localRun", "true"));
            notificationEnabled = Boolean.parseBoolean(getPropertyHandler(property, "notificationEnabled", "true"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static String getPropertyHandler(Properties property, String propertyKey, String defaultValue) {
        return System.getProperty(propertyKey) != null
                ? System.getProperty(propertyKey)
                : property.getProperty(propertyKey, defaultValue);
    }
}
