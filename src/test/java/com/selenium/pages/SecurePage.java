package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage {

    WebDriver driver;

    public SecurePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By successMessage = By.id("flash");
    By logoutButton = By.cssSelector("a.button.secondary.radius");

    // Get Success Message
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    // Click Logout
    public void logout() {
        driver.findElement(logoutButton).click();
    }

    // Verify Login Page after Logout
    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl().contains("/login");
    }

    // Get Current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Get Page Title
    public String getTitle() {
        return driver.getTitle();
    }
}
