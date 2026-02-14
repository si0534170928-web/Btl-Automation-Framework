package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UnemploymentCalculatorPage extends BtlBasePage {

    public UnemploymentCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void fillStopWorkDate() {
        LocalDate date = LocalDate.now().minusMonths(1);
        String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='date'], input[id*='date'], input[id*='Date']")));
        dateField.clear();
        dateField.sendKeys(dateStr);
    }

    public void selectAgeAbove28() {
        WebElement ageOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'מעל 28') or contains(text(),'28')]")));
        ageOption.click();
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'המשך')] | //input[@value='המשך'] | //a[contains(text(),'המשך')]")));
        continueBtn.click();
    }

    public void fillSalaryFields(String amount) {
        List<WebElement> salaryFields = driver.findElements(
                By.cssSelector("input[type='text'][id*='salary'], input[type='number'], input[id*='Salary']"));
        for (WebElement field : salaryFields) {
            if (field.isDisplayed() && field.isEnabled()) {
                field.clear();
                field.sendKeys(amount);
            }
        }
    }

    public boolean isResultsPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'תוצאות חישוב') or contains(text(),'תוצאות')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResultFieldDisplayed(String fieldName) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + fieldName + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
