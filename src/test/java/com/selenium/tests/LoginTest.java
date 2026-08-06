package com.selenium.tests;

import com.selenium.pages.LoginPage;
import com.selenium.pages.SecurePage;
import com.selenium.utils.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() throws Exception {

        Properties config = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            config.load(input);
        }

        String username = System.getProperty("username");
        String password = System.getProperty("password");

        if (username == null || username.isBlank()) {
            username = config.getProperty("username");
        }

        if (password == null || password.isBlank()) {
            password = config.getProperty("password");
        }

        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = new SecurePage(driver);

        System.out.println("========== TEST START ==========");
        System.out.println("Opening URL : " + config.getProperty("base.url"));

        loginPage.openWebsite();
        System.out.println("Website Opened");

        System.out.println("Username : " + username);
        System.out.println("Password Length : " + password.length());

        loginPage.login(username, password);

        // Save page source immediately after login
        Files.writeString(
                Path.of("page-source-after-login.html"),
                driver.getPageSource()
        );

        System.out.println("Saved page-source-after-login.html");

        System.out.println("Login Completed");

        System.out.println("Current URL : " + driver.getCurrentUrl());
        System.out.println("Page Title : " + driver.getTitle());
        System.out.println("Page Source Length : " + driver.getPageSource().length());

        if (securePage.isDashboardDisplayed()) {
            System.out.println("Dashboard Loaded");
        } else {
            System.out.println("Dashboard NOT Loaded");
            System.out.println("After Login URL : " + driver.getCurrentUrl());
            System.out.println("After Login Title : " + driver.getTitle());
        }

        Assertions.assertTrue(
                securePage.isDashboardDisplayed(),
                "Dashboard is not displayed"
        );

        System.out.println("Dashboard Verification Passed");

        System.out.println("Welcome Message : "
                + securePage.getWelcomeMessage());

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Files.createDirectories(Path.of("screenshots"));

        Files.copy(
                src.toPath(),
                Path.of("screenshots/login-success.png"),
                StandardCopyOption.REPLACE_EXISTING
        );

        System.out.println("Screenshot Saved");

        System.out.println("========== TEST END ==========");
    }
}
