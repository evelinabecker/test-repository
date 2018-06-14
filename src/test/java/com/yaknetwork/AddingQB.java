package com.yaknetwork;

import org.junit.AfterClass;
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
 * Should log in to dashboard, navigate to Question Banks section and create a new Question Bank
 */
public class AddingQB {

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

        //selecting Company after successful login
        WebElement chooseCompanyButton = driver.findElement(By.cssSelector(".x-tool-dock.x-listitem-tool-dock.x-component-tool-dock"));
        chooseCompanyButton.click();

        //navigating to Question Banks section
        WebElement questionBanksButton = driver.findElement(By.cssSelector("a[href='/question-banks']"));
        questionBanksButton.click();

        //clicking 'Create new Question bank' button
        WebElement createBankButton = driver.findElement(By.cssSelector(".x-button-el"));
        createBankButton.click();

        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date today = Calendar.getInstance().getTime();
        String todayDate = df.format(today);

        String QBname = "Question bank+" + todayDate;
        WebElement qbNameField = driver.findElement(By.cssSelector("[name=name]"));
        qbNameField.sendKeys(QBname);

        //entering QB description
        WebElement qbDescriptionField = driver.findElement(By.cssSelector("[name=description]"));
        qbDescriptionField.sendKeys("This is a test question bank created by auto test");

        WebElement saveQBButton = driver.findElement(By.cssSelector("form .x-button-el"));
        saveQBButton.click();




    }

    @AfterClass
    public static void tearDown() {
    }
}

