package com.selenium.tests;

import com.selenium.pages.LoginPage;
import com.selenium.pages.SecurePage;
import com.selenium.utils.DriverFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LogoutTest {

    @Test
    public void logoutTest() {

        WebDriver driver = DriverFactory.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = new SecurePage(driver);

        try {

            System.out.println("========== LOGOUT TEST START ==========");

            loginPage.openWebsite();
            System.out.println("Website Opened");

            loginPage.login("tomsmith", "SuperSecretPassword!");
            System.out.println("Login Successful");

            securePage.logout();
            System.out.println("Logout Button Clicked");

            Assertions.assertTrue(securePage.isLoginPageDisplayed());

            System.out.println("Logout Verification Passed");

            System.out.println("========== LOGOUT TEST END ==========");

        } finally {

            driver.quit();

        }
    }
}
