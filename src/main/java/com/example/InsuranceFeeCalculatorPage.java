package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class InsuranceFeeCalculatorPage extends BtlBasePage {

    @FindBy(css = "h1, h2.page-title")
    private WebElement pageHeader;

    public InsuranceFeeCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getText(pageHeader);
    }

    public void selectYeshivaStudent() {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'תלמיד ישיבה')]")));
        option.click();
    }

    public void fillBirthDate() {
        long minDay = LocalDate.now().minusYears(70).toEpochDay();
        long maxDay = LocalDate.now().minusYears(18).minusDays(1).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        String dateStr = randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='date'], input[id*='date'], input[id*='Date'], input[placeholder*='לידה']")));
        dateField.clear();
        dateField.sendKeys(dateStr);
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'המשך')] | //input[@value='המשך'] | //a[contains(text(),'המשך')]")));
        continueBtn.click();
    }

    public boolean isStepTwo() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'צעד שני') or contains(text(),'שלב 2') or contains(text(),'שלב שני')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectNoDisability() {
        WebElement noOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'לא')]")));
        noOption.click();
    }

    public boolean isFinalStep() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'סיום') or contains(text(),'תוצאות') or contains(text(),'סיכום')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getInsuranceFeeResult(String label) {
        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + label + "')]/following-sibling::* | //*[contains(text(),'" + label + "')]/..")));
        return resultElement.getText();
    }

    public boolean isResultContains(String text) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + text + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
