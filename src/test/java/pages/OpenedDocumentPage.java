package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OpenedDocumentPage extends HomePage {

    @FindBy(css = "pdf-viewer#viewer")
    private WebElement pdfViewer;


    public OpenedDocumentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OpenedDocumentPage verifyPdfIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith(".pdf"), "pdf is not displayed");
        return this;
    }
}
