package com.selenium.tests;

import com.selenium.pages.LoginPage;
import com.selenium.utils.DriverFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class InvalidLoginTest {

    @Test
    public void invalidLoginTest() {

        WebDriver driver = DriverFactory.getDriver();

        LoginPage loginPage = new LoginPage(driver);

        try {

            System.out.println("===== INVALID LOGIN TEST =====");

            loginPage.openWebsite();

            loginPage.login("tomsmith", "WrongPassword");

            Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

            System.out.println("Invalid Login Verified");

        } finally {

            driver.quit();

        }
    }
}
