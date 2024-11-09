package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class CheckoutStepTwo extends BasePage{

    By lstProduct = By.xpath("//div[contains(@class,'cart_list')]/div[contains(@class,'cart_item')]");
    By lstProduct_title = By.xpath(".//a[contains(@id,'title_link')]");
    By btnFinish = By.id("finish");

    public CheckoutStepTwo(WebDriver driver) {
        super(driver);
    }

    private WebElement getProduct(String title) {
        return driver.findElements(lstProduct)
                .stream()
                .filter(element -> element.findElement(lstProduct_title).getText().equals(title))
                .findFirst()
                .orElseThrow();
    }

    public CheckoutStepTwo isProductInCheckoutOverview(String title) {
        if(getProduct(title).isDisplayed()){
            return this;
        }else {
            throw new NoSuchElementException("Product missing from the cart");
        }
    }

    public CheckoutComplete finish() {
        driver.findElement(btnFinish).click();
        return new CheckoutComplete(driver);
    }
}
