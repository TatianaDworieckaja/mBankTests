package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ProjectProperties;

import java.util.List;

public class AgenciesPage extends HomePage {
    @FindBy(css = "div#map-container")
    private WebElement mapContainer;

    @FindBy(css = "input#autocomplete")
    private WebElement localionInput;

    @FindBy(xpath = "//tbody[@class='red']/*[2]/*[1]")
    List<WebElement> addresses;


    public AgenciesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AgenciesPage scrollToMapView() {
        scrollPageUntilElementVisible(localionInput);
        return this;
    }

    public AgenciesPage typeLocation(String location) {
        waitUntilWebElement(10, ExpectedConditions.visibilityOf(localionInput));
        localionInput.clear();
        localionInput.sendKeys(location);
        localionInput.sendKeys(Keys.ENTER);
        return this;
    }

    public AgenciesPage verifyAgencyAddressContains(String location){
        verifyElementsOfCollectionContainsText(addresses, location, "Location is not displayed correctly/different from search input");
        return this;
    }

    public void proceedToHomePage(){
        driver.get(ProjectProperties.getBaseUrl());
    }

}
