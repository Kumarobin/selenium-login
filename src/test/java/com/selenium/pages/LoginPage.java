package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Properties properties = new Properties();

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    // Spinner
    private final By spinner = By.cssSelector(".ngx-spinner-overlay");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try (InputStream input =
                     getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openWebsite() {

        String url = System.getProperty("base.url");

        if (url == null || url.isBlank()) {
            url = properties.getProperty("base.url");
        }

        System.out.println("Opening URL : " + url);

        driver.get(url);

        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public void login(String user, String pass) {

        WebElement userField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(username));

        userField.clear();
        userField.sendKeys(user);

        WebElement passField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(password));

        passField.clear();
        passField.sendKeys(pass);

        WebElement loginBtn =
                wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", loginBtn);

        // Wait until spinner disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
