package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import utils.BrowserFactory;
import utils.ProjectProperties;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void openHomePage(){
        driver = BrowserFactory.createDriver();
        driver.get(ProjectProperties.getBaseUrl());
        homePage = new HomePage(driver);
        homePage.acceptGodDamnCookies();
    }

    @AfterClass (alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

}
