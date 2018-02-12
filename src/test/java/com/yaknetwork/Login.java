package com.yaknetwork;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by evelina on 11/15/17.
 */
public class Login {


    private static WebDriver driver;

    @BeforeClass

    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://dashboard-staging.yaknetwork.com/sign-in");

    }

    @Test
    public void userLogin() {
        WebElement emailField = driver.findElement(By.cssSelector("[name=email]"));
        emailField.sendKeys("evelina+master44@speedandfunction.com");

        WebElement passwordField = driver.findElement(By.cssSelector("[name=password]"));
        passwordField.sendKeys("121212");

        WebElement signinButton = driver.findElement(By.cssSelector("form button"));
        signinButton.click();

        WebElement profileButton = driver.findElement(By.cssSelector(".header__user-name"));
        String username = profileButton.getText();

        Assert.assertEquals("Evelina Master", username);

    }

    @AfterClass
    public static void tearDown() {
    }
}