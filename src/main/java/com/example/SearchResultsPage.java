package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BtlBasePage {

    @FindBy(css = "h1, .ms-srch-result-primaryQueryText, h2.page-title")
    private WebElement pageHeader;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getText(pageHeader);
    }
}
