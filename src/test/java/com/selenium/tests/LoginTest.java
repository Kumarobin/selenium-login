package com.selenium.tests;

import com.selenium.pages.LoginPage;
import com.selenium.pages.SecurePage;
import com.selenium.utils.DriverFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LoginTest {

    @Test
    public void loginTest() throws Exception {

        WebDriver driver = DriverFactory.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = new SecurePage(driver);

        try {

            System.out.println("========== TEST START ==========");

            loginPage.openWebsite();
            System.out.println("Website Opened");

            loginPage.login("tomsmith", "SuperSecretPassword!");
            System.out.println("Login Completed");

            Assertions.assertTrue(
                    securePage.getCurrentUrl().contains("/secure"));

            System.out.println("Login Verification Passed");

            System.out.println("Success Message : "
                    + securePage.getSuccessMessage());

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Path.of("screenshots"));

            Files.copy(
                    src.toPath(),
                    Path.of("screenshots/login-success.png"),
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot Saved");

            System.out.println("========== TEST END ==========");

        } finally {

            driver.quit();

        }
    }
}
