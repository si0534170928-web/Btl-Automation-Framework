package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BranchesPage extends BtlBasePage {

    @FindBy(css = "h1")
    private WebElement pageHeader;

    public BranchesPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getText(pageHeader);
    }

    public BranchDetailsPage clickBranch(int index) {
        List<WebElement> branchLinks = driver.findElements(
                By.cssSelector("a[href*='snif'], .branch-item a, table.ms-listviewtable a"));
        wait.until(ExpectedConditions.elementToBeClickable(branchLinks.get(index))).click();
        return new BranchDetailsPage(driver);
    }
}
