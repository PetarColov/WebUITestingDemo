package tests;

import contexts.CheckoutInfoData;
import enums.AppMenu;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalityTests extends BaseTest{
    String username = "standard_user";
    String pass = "secret_sauce";
    String firstProduct = "Sauce Labs Backpack";
    String lastProduct = "Test.allTheThings() T-Shirt (Red)";
    String previousToTheLastItem = "Sauce Labs Onesie";
    String sortByPriceHiLo = "Price (high to low)";

    @Test(groups = {"shopping"})
    public void testShoppingFunctionalities(){
        extentTest.info("Shopping functionalities");
        new Login(driver).login(username, pass);

        new Products(driver)
                .add(firstProduct)
                .add(lastProduct);

        new Cart(driver)
                .open()
                .isProductInCart(firstProduct)
                .isProductInCart(lastProduct)
                .remove(firstProduct);

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

        List<WebElement> listedProducts = new Products(driver)
                .sort(sortByPriceHiLo)
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
}
