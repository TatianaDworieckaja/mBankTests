package tests;

import org.testng.annotations.Test;

public class AcountsPageTests extends BaseTest{

    @Test
    public void verifyAccountPageFooterAndHeader(){
        homePage.openAccountsPageByClickButton()
                .scrollToEndOfPage()
                .verifyFooterColumns()
                .verifyFooterBanners()
                .scrollUpToTop()
                .verifyPageHeaderElements();
    }

    @Test
    public void verifyDocumentsOpen(){
    homePage.openAccountsPageByClickButton()
            .scrollIntoDocumentsSectionView()
            .clickDocumentLink()
            .navigateToOpenedTab()
            .verifyPdfIsDisplayed();
    }
}
