package tests;

import config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DriverFactory;

import java.time.Duration;

public abstract class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    ConfigFileReader configFileReader;

    @BeforeMethod
    public void setup() {
        //"chrome" - Google Chrome
        //"firefox" - Mozilla Firefox
        driver = DriverFactory.open("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getBaseUrl());
    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
}
