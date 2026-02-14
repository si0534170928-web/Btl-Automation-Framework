package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnemploymentTests extends BaseTest {

    @Test(description = "חישוב דמי אבטלה - תסריט 7")
    public void testUnemploymentBenefitCalculation() {
        HomePage homePage = new HomePage(driver);

        // ניווט: קצבאות והטבות -> אבטלה
        homePage.navigateTo(MainMenu.BENEFITS, "אבטלה");
        UnemploymentPage unemploymentPage = new UnemploymentPage(driver);

        // לחיצה על מחשבוני דמי אבטלה -> חישוב סכום דמי אבטלה
        UnemploymentCalculatorPage calcPage = unemploymentPage.clickCalculatorLink();

        // מילוי תאריך הפסקת עבודה + גיל
        calcPage.fillStopWorkDate();
        calcPage.selectAgeAbove28();
        calcPage.clickContinue();

        // מילוי סכומי השתכרות
        calcPage.fillSalaryFields("10000");
        calcPage.clickContinue();

        // בדיקת דף תוצאות
        Assert.assertTrue(calcPage.isResultsPageDisplayed(), "דף תוצאות חישוב לא מוצג");
        Assert.assertTrue(calcPage.isResultFieldDisplayed("שכר יומי ממוצע"),
                "שכר יומי ממוצע לא מוצג");
        Assert.assertTrue(calcPage.isResultFieldDisplayed("דמי אבטלה ליום"),
                "דמי אבטלה ליום לא מוצג");
        Assert.assertTrue(calcPage.isResultFieldDisplayed("דמי אבטלה לחודש"),
                "דמי אבטלה לחודש לא מוצג");
    }
}
