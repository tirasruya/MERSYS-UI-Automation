package utils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static final Logger LOGGER =
            LogManager.getLogger(ConfigReader.class);

    private static Properties properties;

    static {
        LOGGER.info("Loading config.properties");
        try {
            properties = new Properties();
            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream("config.properties");
            properties.load(input);
        } catch (Exception e) {
            LOGGER.fatal("Failed to load config.properties", e);
            throw new RuntimeException("config.properties could not be loaded", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}