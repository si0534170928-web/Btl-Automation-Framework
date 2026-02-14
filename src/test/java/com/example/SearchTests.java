package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @Test(description = "חיפוש וודא תוצאות - תסריט 3")
    public void testSearchAndVerifyResults() {
        test = extent.createTest("חיפוש וודא תוצאות");

        HomePage homePage = new HomePage(driver);
        test.info("מבצע חיפוש: חישוב סכום דמי לידה ליום");

        SearchResultsPage resultsPage = homePage.search("חישוב סכום דמי לידה ליום");
        String header = resultsPage.getHeaderText();

        test.info("כותרת שהתקבלה: " + header);
        Assert.assertTrue(header.contains("תוצאות חיפוש") && header.contains("חישוב סכום דמי לידה ליום"),
                "הכותרת אינה מכילה את התוצאה הצפויה");
        test.pass("תוצאות החיפוש תקינות");
    }
}
