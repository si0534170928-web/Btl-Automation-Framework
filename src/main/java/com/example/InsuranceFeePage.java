package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InsuranceFeePage extends BtlBasePage {

    @FindBy(css = "h1")
    private WebElement pageHeader;

    public InsuranceFeePage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getText(pageHeader);
    }

    public InsuranceFeeCalculatorPage clickCalculatorLink() {
        WebElement calcLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'מחשבון לחישוב דמי ביטוח')]")));
        calcLink.click();
        return new InsuranceFeeCalculatorPage(driver);
    }
}
