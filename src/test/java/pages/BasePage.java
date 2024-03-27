package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.BrowserFactory;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public abstract class BasePage {

    protected SoftAssert softAssert = new SoftAssert();
    protected Actions actions = new Actions(BrowserFactory.getDriver());
    public Object scrollPageUntilElementVisible(WebElement element) {
        ((JavascriptExecutor) BrowserFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitUntilWebElement(10, ExpectedConditions.visibilityOf(element));
        return this;
    }

    public Object switchToTheTab() {
        String originalTabHandle = BrowserFactory.getDriver().getWindowHandle();
        Set<String> allWindowHandles = BrowserFactory.getDriver().getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            BrowserFactory.getDriver().switchTo().window(windowHandle);
        }
        allWindowHandles.remove(originalTabHandle);//optional - remove primary (previous) tab
        return new Object();
    }

    public void waitUntilBoolean(long seconds, ExpectedCondition<Boolean> expectedConditions) {
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), Duration.ofSeconds(seconds));
        wait.until(expectedConditions);
    }

    public void waitUntilWebElement(long seconds, ExpectedCondition<WebElement> expectedConditions) {
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), Duration.ofSeconds(seconds));
        wait.until(expectedConditions);
    }

    public void verifyElementsOfCollectionDisplayed(List<WebElement> collection, String errorMessage, int size){
        for(WebElement element:collection){
            softAssert.assertTrue(element.isDisplayed(), errorMessage);
        }
        softAssert.assertTrue(collection.size() == size);
        softAssert.assertAll();
    }

    public Object navigateToElement(WebElement element) {
        actions.moveToElement(element);
        actions.build().perform();
        return new Object();
    }

    public void verifyElementsOfCollectionContainsText(List<WebElement> collection, String text, String errorMessage){
        for(WebElement element:collection){
            Assert.assertTrue(element.getText().contains(text), errorMessage);
        }
    }

}

