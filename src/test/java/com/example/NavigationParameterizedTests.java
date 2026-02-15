package com.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NavigationParameterizedTests extends BaseTest {

    @DataProvider(name = "benefitPages")
    public Object[][] benefitPages() {
        return new Object[][]{
                {"אבטלה", "אבטלה"},
                {"אמהות", "אמהות"},
                {"ילדים", "ילדים"},
                {"נכות כללית", "נכות כללית"},
                {"סיעוד", "סיעוד"}
        };
    }

    @Test(dataProvider = "benefitPages", description = "ניווט לדפי קצבאות ובדיקת breadcrumbs - תסריט 8")
    public void testNavigationAndBreadcrumbs(String subMenu, String expectedBreadcrumb) {
        test = extent.createTest("ניווט ל-" + subMenu);

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(MainMenu.BENEFITS, subMenu);

        BtlBasePage currentPage = new BtlBasePage(driver);
        String breadcrumb = currentPage.getBreadcrumbText();

        Assert.assertTrue(breadcrumb.contains(expectedBreadcrumb),
                "Breadcrumb לא מכיל: " + expectedBreadcrumb + ". התקבל: " + breadcrumb);
    }
}
