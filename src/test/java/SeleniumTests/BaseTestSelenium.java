package SeleniumTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.DriverFactory;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTestSelenium {
    ExtentReports extent;
    ExtentTest extentTest;
    WebDriver driver;
    WebDriverWait wait;
    ConfigFileReader configFileReader;

    @BeforeClass
    public void beforeClass(){
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter ("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup(Method method) {
        //"chrome" - Google Chrome
        //"firefox" - Mozilla Firefox
        driver = DriverFactory.open("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        configFileReader = new ConfigFileReader();
        driver.manage().window().maximize();
        driver.get(configFileReader.getBaseUrl());
        extentTest = extent.createTest(method.getName());
        extentTest.info("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if(!result.isSuccess()){
            extentTest.fail(result.getMethod().getMethodName() + " failed");
        }
        driver.close();
        extent.flush();
    }
}
