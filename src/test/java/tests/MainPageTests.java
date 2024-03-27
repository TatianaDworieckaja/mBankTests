package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test
    public void verifyMainPageContent() {
        homePage.verifyHomePageUrl()
                .verifyTopMenu()
                .verifySubMenu()
                .verifyLogo()
                .verifyMainContent();
    }

    @Test
    public void verifyOpeningAccountsPage() {
        homePage.openAccountsPageByClickButton()
                .verifyAccountPageUrl();
    }

    @Test(dataProvider = "actionsMenu-provider")
    public void verifyActionsMenuTipsDisplayed(String itemName) {
        homePage.navigateToElement(itemName)
                .verifyTooltipDisplayed(itemName);
    }

    @DataProvider(name = "actionsMenu-provider")
    public Object[][] loginData() {
        return new Object[][]{
                {"uk"},
                {"searchengine"},
                {"contact"},
                {"agencies"}
        };

    }
}
