package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.ProjectProperties;

import java.util.List;

public class HomePage extends BasePage {

    protected WebDriver driver;

    @FindBy(id="page-head")
    protected WebElement pageHeader;
    @FindBy(css = "#logotype")
    protected WebElement logo;

    @FindBy(xpath = "//li[contains (@data-level, '1')]")
    private List<WebElement> topMenu;

    @FindBy(xpath = "//li[contains (@data-level, '2')]")
    private  List<WebElement> subMenu;

    @FindBy(id = "main")
    private WebElement mainContent;

    @FindBy(id = "page-foot")
    private WebElement footer;
    @FindBy(css = "li#cta-button")
    private WebElement zalozKontoButton;
    @FindBy(xpath = "(//a[contains(text(),'Konta')])[1]")
    private WebElement kontaLink;
    @FindBy(xpath = "//li[@id='agencies']")
    protected WebElement agenciesIcon;


    private String actionItemPattern = "li#%s>*:first-child";

    public HomePage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }


    public HomePage verifyHomePageUrl(){
        Assert.assertEquals(driver.getCurrentUrl(), ProjectProperties.getBaseUrl(), "Main page is invalid");
        return this;
    }

    public HomePage verifyTopMenu(){
        verifyElementsOfCollectionDisplayed(topMenu,"One or more elements of topmenu are not displayed", 4 );
        return this;
    }

    public HomePage verifySubMenu(){
        verifyElementsOfCollectionDisplayed(subMenu,"One or more elements of submenu are not displayed", 9 );
        return this;
    }

    public HomePage verifyLogo(){
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
        return this;
    }

    public HomePage verifyMainContent(){
        List<WebElement> list = mainContent.findElements(By.tagName("section"));
        for (WebElement element:list) {
           scrollPageUntilElementVisible(element);
           waitUntilWebElement(10, ExpectedConditions.visibilityOf(element));
           Assert.assertTrue(element.isDisplayed(), "One or more elements of maincontent are not displayed");
        }
        return this;
    }

    public void acceptGodDamnCookies() {
        WebElement shadowHost = driver.findElement(By.className("cookieConsent"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("button:nth-of-type(3)")).click();
    }

    public AccountsPage openAccountsPageByClickButton(){
        zalozKontoButton.click();
        switchToTheTab();
        return new AccountsPage(driver);
    }

    private WebElement getActionMenuItem(String actionName){
        return driver.findElement(By.cssSelector(String.format(actionItemPattern, actionName)));
    }

    public HomePage navigateToElement(String itemName) {
        WebElement menuItem = getActionMenuItem(itemName);
        navigateToElement(menuItem);
        waitUntilBoolean(10, ExpectedConditions.attributeContains(menuItem, "class", "active"));
        return this;
    }

    public void verifyTooltipDisplayed(String itemName){
        String valueOfAttribute = getActionMenuItem(itemName).getAttribute("class");
        Assert.assertTrue(valueOfAttribute.contains("active"), "Tip for + " + itemName + " is not displayed correctly");
    }

    public AgenciesPage clickAgenciesIcon(){
        agenciesIcon.click();
        return new AgenciesPage(driver);
    }
}
