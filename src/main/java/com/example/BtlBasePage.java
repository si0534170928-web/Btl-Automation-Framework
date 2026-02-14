package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BtlBasePage extends BasePage {

    @FindBy(css = "input[type='search'], input.ms-srch-sb-input, input[id*='SearchBox']")
    private WebElement searchInput;

    @FindBy(css = "a.ms-srch-sb-searchLink, .search-icon, [title='חיפוש']")
    private WebElement searchButton;

    @FindBy(css = "a[href*='snifim']")
    private WebElement branchesLink;

    public BtlBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickMainMenu(MainMenu menu) {
        WebElement menuElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + menu.getMenuText() + "')]")));
        menuElement.click();
    }

    public void clickSubMenu(String subMenuText) {
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + subMenuText + "')]")));
        subMenu.click();
    }

    public void navigateTo(MainMenu menu, String subMenu) {
        clickMainMenu(menu);
        clickSubMenu(subMenu);
    }

    public SearchResultsPage search(String text) {
        fillText(searchInput, text);
        click(searchButton);
        return new SearchResultsPage(driver);
    }

    public BranchesPage clickBranches() {
        click(branchesLink);
        return new BranchesPage(driver);
    }

    public String getBreadcrumbText() {
        WebElement breadcrumb = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".breadcrumb, #breadcrumb, .ms-breadcrumb, #s4-breadcrumb")));
        return breadcrumb.getText();
    }
}
