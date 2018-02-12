package com.yaknetwork;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This test checks sign-up logic
 * @author evelina
 */
public class SignupTest {

    private static final String APP_URL = "https://staging-test-game.yaknetwork.com/app/5a71dfd11f802d000fba0ffb";
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(APP_URL + "/sign-up");
    }

    /**
     * Should register a user successfully
     */
    @Test
    public void shouldSignupAUser() {

        WebElement firstNameField = driver.findElement(By.cssSelector("[name=firstName]"));
        firstNameField.sendKeys("Auto");

        WebElement lastNameField = driver.findElement(By.cssSelector("[name=lastName]"));
        lastNameField.sendKeys("Test");

        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date today = Calendar.getInstance().getTime();
        String todayDate = df.format(today);

        String newemail = "evelina+" + todayDate + "@mailinator.com";
        WebElement emailField = driver.findElement(By.cssSelector("[name=email]"));
        emailField.sendKeys(newemail);

        WebElement firstPasswordField = driver.findElement(By.cssSelector("[name=password]"));
        firstPasswordField.sendKeys("testTEST1");

        WebElement secondPasswordField = driver.findElement(By.cssSelector("[name=confirmPassword]"));
        secondPasswordField.sendKeys("testTEST1");

        WebElement checkbox = driver.findElement(By.cssSelector("[name=accept]"));
        checkbox.click();

        WebElement signUpButton = driver.findElement(By.cssSelector("[type=submit]"));
        signUpButton.click();

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, APP_URL + "/lobby");
    }

    @AfterClass
    public static void tearDown() {
    }
}