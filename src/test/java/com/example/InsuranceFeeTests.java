package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InsuranceFeeTests extends BaseTest {

    @Test(description = "חישוב דמי ביטוח לתלמיד ישיבה - תסריט 6")
    public void testInsuranceFeeForYeshivaStudent() {
        test = extent.createTest("חישוב דמי ביטוח לתלמיד ישיבה");

        HomePage homePage = new HomePage(driver);

        // ניווט: דמי ביטוח -> דמי ביטוח לאומי
        homePage.navigateTo(MainMenu.INSURANCE, "דמי ביטוח לאומי");
        InsuranceFeePage insuranceFeePage = new InsuranceFeePage(driver);
        test.info("כותרת דף: " + insuranceFeePage.getHeaderText());
        Assert.assertTrue(insuranceFeePage.getHeaderText().contains("דמי ביטוח לאומי"),
                "כותרת הדף אינה דמי ביטוח לאומי");

        // לחיצה על מחשבון
        InsuranceFeeCalculatorPage calcPage = insuranceFeePage.clickCalculatorLink();
        test.info("כותרת מחשבון: " + calcPage.getHeaderText());
        Assert.assertTrue(calcPage.getHeaderText().contains("חישוב דמי ביטוח"),
                "כותרת המחשבון אינה תקינה");

        // צעד ראשון: בחירת תלמיד ישיבה + תאריך לידה
        calcPage.selectYeshivaStudent();
        calcPage.fillBirthDate();
        calcPage.clickContinue();
        test.info("צעד ראשון הושלם");

        // צעד שני
        Assert.assertTrue(calcPage.isStepTwo(), "לא הגענו לצעד שני");
        calcPage.selectNoDisability();
        calcPage.clickContinue();
        test.info("צעד שני הושלם");

        // סיום - בדיקת תוצאות
        Assert.assertTrue(calcPage.isFinalStep(), "לא הגענו לשלב סיום");
        Assert.assertTrue(calcPage.isResultContains("43"), "דמי ביטוח לאומי אינם 43 ש\"ח");
        Assert.assertTrue(calcPage.isResultContains("120"), "דמי ביטוח בריאות אינם 120 ש\"ח");
        Assert.assertTrue(calcPage.isResultContains("163"), "סך הכל אינו 163 ש\"ח");
        test.pass("תוצאות החישוב תקינות: 43 + 120 = 163 ש\"ח");
    }
}
