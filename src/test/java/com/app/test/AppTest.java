package com.app.test;

import com.app.pages.AddtoBag;
import com.app.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class AppTest {
    protected WebDriver driver;
    protected Properties properties;
    LoginPage login;
    AddtoBag addToBag;

    @BeforeMethod
    public void setUp() throws IOException {
        String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        login=new LoginPage(driver);
        addToBag =new AddtoBag(driver);
        //props = new Properties();
        String rootFolder=System.getProperty("user.dir");
         properties = new Properties();
        properties.load(new FileInputStream(rootFolder + "/src/main/resources/data.properties"));




    }

    @Test
    public void verifyLoginPage() throws IOException, InterruptedException {

        driver.get(properties.getProperty("loginUrl"));
        login.verifyLogin();
    }
    @Test
    public void verifyAddToCart()
    {
        driver.get(properties.getProperty("url"));
        addToBag.verifyAddToBag();
    }
    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}
