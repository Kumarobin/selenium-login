package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecurePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By welcomeText =
            By.xpath("//span[contains(text(),'Welcome')]");

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isDashboardDisplayed() {

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));

            System.out.println("After Login URL : " + driver.getCurrentUrl());
            System.out.println("After Login Title : " + driver.getTitle());

            return true;

        } catch (Exception e) {

            System.out.println("Dashboard NOT Loaded");
            return false;
        }
    }

    public String getWelcomeMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(welcomeText)
        ).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
