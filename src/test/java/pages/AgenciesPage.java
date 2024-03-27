package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgenciesPage extends HomePage {

    @FindBy(xpath = "//li[@id='agencies']")
    private WebElement agencies;

    @FindBy(css = "div#map-container")
    private WebElement mapContainer;

    @FindBy(css = "input#autocomplete")
    private WebElement localionInput;


    public AgenciesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AgenciesPage scrollToMapView() {
        scrollPageUntilElementVisible(mapContainer);
        return this;
    }

    public AgenciesPage typeLocation() {
        waitUntilWebElement(10, ExpectedConditions.visibilityOf(localionInput));
        localionInput.sendKeys("Warszawa");
        localionInput.sendKeys(Keys.ENTER);
        return this;
    }

    public void verifyLocationOnMap(){

    }

}
