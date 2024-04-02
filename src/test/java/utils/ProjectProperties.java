package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProperties {

    public static  Properties getProperties(){
        Properties properties = new Properties();
        try (final InputStream inputStream = ProjectProperties.class.getClassLoader().getResourceAsStream("project.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getBaseUrl(){
        return getProperties().getProperty("baseUrl");
    }

    public static String getBrowserType(){
        return getProperties().getProperty("browserType");
    }
}
