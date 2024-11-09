package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import contexts.CheckoutInfoData;
import enums.AppMenu;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalityTests extends BaseTest{
    ExtentReports extent;
    ExtentTest extentTest;
    String username = "standard_user";
    String pass = "secret_sauce";

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter ("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method){
        extentTest = extent.createTest(method.getName());
        extentTest.info("Starting test: " + method.getName());
    }


    @Test(groups = {"shopping"})
    public void testShoppingFunctionalities(){
        extentTest.info("Shopping functionalities");
        new Login(driver).login(username, pass);

        String firstProduct = "Sauce Labs Backpack";
        String lastProduct = "Test.allTheThings() T-Shirt (Red)";

        new Products(driver)
                .add(firstProduct)
                .add(lastProduct);

        new Cart(driver)
                .open()
                .isProductInCart(firstProduct)
                .isProductInCart(lastProduct)
                .remove(firstProduct);

        String previousToTheLastItem = "Sauce Labs Onesie";
        new Cart(driver)
                .continueShopping()
                .add(previousToTheLastItem);

        new Cart(driver)
                .open()
                .isProductInCart(lastProduct)
                .isProductInCart(previousToTheLastItem);

        CheckoutInfoData data = new CheckoutInfoData();
        data.setFirstName("John");
        data.setLastName("Doe");
        data.setZip("3000");

        new Cart(driver)
                .checkout()
                .setInformation(data)
                .continueBtn()
                .isProductInCheckoutOverview(lastProduct)
                .isProductInCheckoutOverview(previousToTheLastItem)
                .finish();

        new CheckoutComplete(driver)
                .backHomeBtn();

        new Cart(driver)
                .open()
                .validateCartIsEmpty();

        new Header(driver)
                .navigateToMenu(AppMenu.LOGOUT);
        extentTest.pass("Shopping functionalities successful");
    }

    @Test(groups = {"sorting"})
    public void testSorting(){
        extentTest.info("Sorting functionalities");
        new Login(driver)
                .login(username, pass);
        String sortBy = "Price (high to low)";

        List<WebElement> listedProducts = new Products(driver)
                .sort(sortBy)
                .getListedProducts();

        List<Double> pricesAfterSort = new ArrayList<>();
        for (WebElement priceElement : listedProducts) {
            Double price = Double.parseDouble(priceElement.getText().replace("$", ""));
            pricesAfterSort.add(price);
        }
        List<Double> sortedPricesHiLo = pricesAfterSort.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Assert.assertEquals(pricesAfterSort, sortedPricesHiLo);

        new Header(driver)
                .navigateToMenu(AppMenu.LOGOUT);
        extentTest.pass("Sorting functionalities successful");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result){
        if(!result.isSuccess()){
            extentTest.fail(result.getMethod().getMethodName() + " failed");
        }
        driver.close();
        extent.flush();
    }
}
