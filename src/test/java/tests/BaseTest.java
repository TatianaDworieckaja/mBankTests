package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.BrowserFactory;
import utils.ProjectProperties;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        driver = BrowserFactory.createDriver();
        driver.get(ProjectProperties.getBaseUrl());
        homePage = new HomePage(driver);
        //homePage.acceptGodDamnCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

}
