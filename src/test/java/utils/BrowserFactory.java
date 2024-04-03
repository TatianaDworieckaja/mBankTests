package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    private static WebDriver driver;

    public  static WebDriver createDriver() {
        switch (ProjectProperties.getBrowserType()) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("user-data-dir=C:\\Users\\dworiet1\\Documents\\UserData");
                chromeOptions.addArguments("profile-directory=Default");
                chromeOptions.addArguments("--disable-session-crashed-bubble");
                driver = new ChromeDriver(chromeOptions);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
