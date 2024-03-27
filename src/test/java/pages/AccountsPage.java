package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.ProjectProperties;

import java.util.List;

public class AccountsPage extends HomePage {

    @FindBy(xpath = "//a[@title='na górę strony']")
    private WebElement scrollUpArrow;

    @FindBy(xpath = "//div[@class='row under']")
    private WebElement footer;

    @FindBy(id = "log-in")
    private WebElement zalogujLink;

    @FindBy(id = "main-banner-carousel")//css: section#main-banner-carousel
    private WebElement mainBanner;

    @FindBy(xpath = "//td[@class = 'toggle']")
    List<WebElement> footerColumns;
    @FindBy(xpath = "//div[@class='footer-box']")
    List<WebElement> footerBoxes;

    @FindBy(xpath = "//h2[contains(text(),'Przydatne dokumenty')]")
    private WebElement documentsSectionHeader;

    @FindBy(css = "section.attachment-box > *:first-child :nth-child(2)")
    private WebElement firstDocumentInList;

    public AccountsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AccountsPage verifyAccountPageUrl() {
        Assert.assertEquals(driver.getCurrentUrl(), ProjectProperties.getBaseUrl() + "konta/konta-osobiste/ekonto-osobiste/?ref=hp_przy_zaloguj_nowy_kl", "AccountsPage is invalid");
        return this;
    }

    public AccountsPage scrollToEndOfPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    public AccountsPage verifyFooterColumns() {
        verifyElementsOfCollection(footerColumns, "The column in footer is not displayed", 6);
        return this;
    }

    public AccountsPage verifyFooterBanners() {
        verifyElementsOfCollection(footerBoxes, "The banner in footer is not displayed", 2);
        return this;
    }

    public AccountsPage scrollUpToTop() {
        scrollUpArrow.click();
        waitUntilWebElement(10, ExpectedConditions.visibilityOf(pageHeader));
        return this;
    }

    public AccountsPage verifyPageHeaderElements() {
        softAssert.assertTrue(zalogujLink.isDisplayed(), "Link 'Zaloguj się' is not displayed ");
        softAssert.assertTrue(mainBanner.isDisplayed(), "Main banner on  top of the page is not displayed");
        softAssert.assertAll();
        return this;
    }

    public AccountsPage scrollIntoDocumentsSectionView() {
        scrollPageUntilElementVisible(firstDocumentInList);
        waitUntilWebElement(10, ExpectedConditions.visibilityOf(firstDocumentInList));
        return this;
    }

    public AccountsPage clickDocumentLink() {
        firstDocumentInList.click();
        return this;
    }

    public OpenedDocumentPage navigateToOpenedTab() {
        switchToTheTab();
        return new OpenedDocumentPage(driver);
    }

    @Override
    public AgenciesPage clickAgenciesIcon() {
        return super.clickAgenciesIcon();
    }
}
