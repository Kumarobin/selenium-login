package com.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LoginTest {

    @Test
    public void loginTest() throws Exception {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {

            System.out.println("========== TEST START ==========");

            driver.get("https://the-internet.herokuapp.com/login");
            System.out.println("Website Opened");

            driver.findElement(By.id("username")).sendKeys("tomsmith");
            System.out.println("Username Entered");

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            System.out.println("Password Entered");

            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("Login Button Clicked");

            Thread.sleep(2000);

            // Verify URL
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL : " + currentUrl);

            Assertions.assertTrue(currentUrl.contains("/secure"));
            System.out.println("Login Verification Passed");

            // Verify Success Message
            String successMessage = driver.findElement(By.id("flash")).getText();

            System.out.println("Success Message : " + successMessage);

            Assertions.assertTrue(
                    successMessage.contains("You logged into a secure area!")
            );

            System.out.println("Success Message Verified");

            // Screenshot
            File sourceFile =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshotFolder = new File("screenshots");

            if (!screenshotFolder.exists()) {
                screenshotFolder.mkdirs();
            }

            File destinationFile =
                    new File(screenshotFolder, "login-success.png");

            Files.copy(
                    sourceFile.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot Saved : "
                            + destinationFile.getAbsolutePath()
            );

            System.out.println("Page Title : " + driver.getTitle());

            System.out.println("========== TEST END ==========");

        } finally {

            driver.quit();

        }
    }
}
