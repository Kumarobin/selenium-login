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

        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = new SecurePage(driver);

        System.out.println("========== TEST START ==========");

        loginPage.openWebsite();
        System.out.println("Website Opened");

        loginPage.login(
                config.getProperty("username"),
                config.getProperty("password")
        );

        System.out.println("Login Completed");

        System.out.println("Current URL : " + driver.getCurrentUrl());
        System.out.println("Page Title : " + driver.getTitle());
        System.out.println("Page Source Length : " + driver.getPageSource().length());

        Files.writeString(
                Path.of("page-source.html"),
                driver.getPageSource()
        );

        System.out.println("Page source saved to page-source.html");

        Assertions.assertTrue(
                securePage.isDashboardDisplayed(),
                "Dashboard is not displayed"
        );

        System.out.println("Dashboard Verification Passed");

        System.out.println(
                "Welcome Message : "
                        + securePage.getWelcomeMessage()
        );

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
