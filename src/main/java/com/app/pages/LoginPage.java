package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.testng.Assert;

public class LoginPage {

    WebDriver driver;
    Properties properties;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void fetchProperties() throws IOException {
        String rootFolder=System.getProperty("user.dir");
        properties = new Properties();
        properties.load(new FileInputStream(rootFolder + "/src/main/resources/data.properties"));
    }
    public void verifyLogin() throws IOException, InterruptedException {
        fetchProperties();
        driver.findElement(By.id("mobileNumberPass")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.xpath("//*[@id=\"reactPageContent\"]/div/div/form/div/div[2]/input")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
        Thread.sleep(34000);
        driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
        String actualPageTitle=driver.getTitle();
        //Online Shopping for Women, Men, Kids Fashion & Lifestyle -
        String expectedPageTitle="Myntra";
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Test Failed : Title Not Matched");
    }

}
