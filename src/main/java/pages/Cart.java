package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class Cart extends BasePage{
    By lstProduct = By.xpath("//div[contains(@class,'cart_list')]/div[contains(@class,'cart_item')]");
    By lstProduct_title = By.xpath(".//a[contains(@id,'title_link')]");
    By lstProduct_remove = By.xpath(".//button[contains(@class,'cart_button')][contains(@id,'remove')]");
    By btnCart = By.id("shopping_cart_container");
    By btnContinueShopping = By.id("continue-shopping");
    By btnCheckout = By.id("checkout");
    By cartItems = By.className("cart_item");

    public Cart(WebDriver driver) {
        super(driver);
    }

    private WebElement getProduct(String title) {
        return driver.findElements(lstProduct)
                .stream()
                .filter(element -> element.findElement(lstProduct_title).getText().equals(title))
                .findFirst()
                .orElseThrow();
    }

    public Cart isProductInCart(String title) {
        if(getProduct(title).isDisplayed()){
            return this;
        }else {
            throw new NoSuchElementException("Product missing from the cart");
        }
    }

    public Cart open() {
        driver.findElement(btnCart).click();
        return this;
    }

    public Cart remove(String title) {
        getProduct(title)
                .findElement(lstProduct_remove)
                .click();
        return this;
    }

    public Products continueShopping(){
        driver.findElement(btnContinueShopping).click();
        return new Products(driver);
    }

    public CheckoutStepOne checkout() {
        driver.findElement(btnCheckout).click();
        return new CheckoutStepOne(driver);
    }

    public void validateCartIsEmpty(){
        List<WebElement> elements = driver.findElements(cartItems);
        if (!elements.isEmpty()){
            throw new IllegalStateException("Product missing from the cart");
        }
    }
}
