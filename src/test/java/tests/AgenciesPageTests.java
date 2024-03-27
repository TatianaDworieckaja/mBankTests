package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AgenciesPageTests extends BaseTest{

    @Test(dataProvider = "locations-provider")
    public void verifyMap(String location){
        homePage.openAccountsPageByClickButton()
                .clickAgenciesIcon()
                .scrollToMapView()
                .typeLocation(location)
                .verifyAgencyAddressContains(location)
                .proceedToHomePage();
    }

    @DataProvider(name = "locations-provider")
    public Object[][] loginData() {
        return new Object[][]{
                {"Warszawa"},
                {"Kraków"},
                {"Wrocław"},
                {"Łódź"}
        };

    }
}
