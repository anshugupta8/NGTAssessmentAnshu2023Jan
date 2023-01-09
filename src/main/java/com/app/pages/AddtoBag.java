package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class AddtoBag {

    WebDriver driver;
    Properties properties;
    public AddtoBag(WebDriver driver)
    {
        this.driver=driver;
    }
    public void fetchProperties() throws IOException {
        String rootFolder=System.getProperty("user.dir");
        properties = new Properties();
        properties.load(new FileInputStream(rootFolder + "/src/main/resources/data.properties"));
    }
    public void verifyAddToBag()
    {
        System.out.println("OK");
        WebElement m= driver.findElement(By.xpath("//input"));
        m.sendKeys("Bag");
        m.sendKeys(Keys.ENTER);
        System.out.println("DONE");

        driver.findElement(By.xpath("//picture/img")).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        driver.findElement(By.xpath("//button/p")).click();
        driver.findElement(By.xpath("//div[.=\"ADD TO BAG\"]")).click();

        String actualProductTitle=driver.findElement(By.xpath("//h1[@class=\"pdp-title\"]")).getText();
        System.out.println(actualProductTitle);

        driver.findElement(By.xpath("//header/div[2]/div[2]/a[2]/span[2]")).click();

        String expected="1/1 ITEMS SELECTED";
        String actualItemText = driver.findElement(By.xpath("//div[@class='bulkActionStrip-message']")).getText();

        Assert.assertEquals(actualItemText,expected,"TEST FAILED : NOT ADDED");


    }
}
