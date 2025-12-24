package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseDriver {

    private static final Logger LOGGER =
            LogManager.getLogger(BaseDriver.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserThread = new ThreadLocal<>();

    private BaseDriver() {}

    public static void setBrowser(String browser) {
        browserThread.set(browser);
        LOGGER.info("Browser set to: {}", browser);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver(browserThread.get()));
        }
        LOGGER.debug("Getting WebDriver instance for browser: {}", browserThread.get());
        return driver.get();
    }

    private static WebDriver createDriver(String browser) {

        boolean headless =
                Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }

        LOGGER.info("Creating WebDriver for browser: {}", browser);
        LOGGER.info("Headless mode: {}", headless);

        switch (browser.toLowerCase()) {

            case "chrome":
                LOGGER.debug("Initializing ChromeDriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);

            case "firefox":
                LOGGER.debug("Initializing FirefoxDriver");
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }

                WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;

            default:
                LOGGER.error("Unsupported browser requested: {}", browser);
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        browserThread.remove();
        LOGGER.info("Quitting WebDriver");
    }
}