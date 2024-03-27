package tests;

import org.testng.annotations.Test;

public class AgenciesPageTests extends BaseTest{

    @Test
    public void verifyMap(){
        homePage.openAccountsPageByClickButton()
                .clickAgenciesIcon()
                .scrollToMapView()
                .typeLocation();
              //  .verifyLocalizationOnMap();
    }

    /*@Test(dependsOnMethods = "verifyMap")
    public void verifyBankomatyTab(){
        homePage.clickAgenciesIcon()
                .scrollToMapView()
                .goToBankomatyTab()
                .typeLocalization()
                .verifyLocalizationOnLeft();
    }*/
}
