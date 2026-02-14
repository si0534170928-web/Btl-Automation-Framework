package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BranchesTests extends BaseTest {

    @Test(description = "כניסה לדף סניפים - תסריט 4")
    public void testNavigateToBranches() {
        test = extent.createTest("כניסה לדף סניפים");

        HomePage homePage = new HomePage(driver);
        BranchesPage branchesPage = homePage.clickBranches();

        String header = branchesPage.getHeaderText();
        test.info("כותרת דף סניפים: " + header);
        Assert.assertTrue(header.contains("סניפים") && header.contains("שירות"),
                "הכותרת אינה מכילה סניפים וערוצי שירות");
        test.pass("דף סניפים וערוצי שירות עלה בהצלחה");
    }

    @Test(description = "בדיקת פרטי סניף - תסריט 5")
    public void testBranchDetails() {
        HomePage homePage = new HomePage(driver);
        BranchesPage branchesPage = homePage.clickBranches();
        BranchDetailsPage detailsPage = branchesPage.clickBranch(0);

        Assert.assertTrue(detailsPage.isAddressDisplayed(), "כתובת לא מוצגת");
        Assert.assertTrue(detailsPage.isReceptionDisplayed(), "קבלת קהל לא מוצגת");
        Assert.assertTrue(detailsPage.isPhoneDisplayed(), "מענה טלפוני לא מוצג");
    }
}
