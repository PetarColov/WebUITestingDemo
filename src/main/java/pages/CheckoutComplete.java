package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutComplete extends BasePage{
    By btnBackHome = By.id("back-to-products");

    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    public Products backHomeBtn(){
        driver.findElement(btnBackHome).click();
        return new Products(driver);
    }
}
