package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UnemploymentPage extends BtlBasePage {

    public UnemploymentPage(WebDriver driver) {
        super(driver);
    }

    public UnemploymentCalculatorPage clickCalculatorLink() {
        WebElement calcLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'מחשבוני דמי אבטלה')]")));
        calcLink.click();

        WebElement amountCalc = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'חישוב סכום דמי אבטלה')]")));
        amountCalc.click();

        return new UnemploymentCalculatorPage(driver);
    }
}
