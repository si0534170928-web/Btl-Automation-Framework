package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BranchDetailsPage extends BtlBasePage {

    public BranchDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAddressDisplayed() {
        return isFieldDisplayed("כתובת");
    }

    public boolean isReceptionDisplayed() {
        return isFieldDisplayed("קבלת קהל");
    }

    public boolean isPhoneDisplayed() {
        return isFieldDisplayed("מענה טלפוני");
    }

    private boolean isFieldDisplayed(String fieldName) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + fieldName + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
