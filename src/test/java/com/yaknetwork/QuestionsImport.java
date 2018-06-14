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
 * This test should import questions
 */
public class QuestionsImport {
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

        //navigating to specific QB
        WebElement viewQBButton = driver.findElement(By.cssSelector("a[href^='/question-banks/5ab42ef1639f93000f0c81ed/bank-questions']"));
        viewQBButton.click();

        //clicking 'Import' button
        WebElement importButton = driver.findElement(By.cssSelector(".dashboard__buttons-list li:nth-child(2)"));
        importButton.click();

        WebElement browseButton = driver.findElement(By.cssSelector("[type=file]"));
        browseButton.sendKeys("/Users/evelina/Downloads/csvExample (12).csv");

        WebElement saveButton = driver.findElement(By.cssSelector(".popup-import-questions .popup-import-questions__save button"));
        saveButton.click();

        WebElement importInformBox = driver.findElement(By.cssSelector(".x-messagebox-body-el"));
        String informMessage = importInformBox.getText();

        Assert.assertEquals("Your file has been successfully imported.", informMessage);

        WebElement importedQuestion = driver.findElement(By.xpath("/table/tr/td[text()='One']"));

    }

        @AfterClass
        public static void tearDown () {
        }

}
