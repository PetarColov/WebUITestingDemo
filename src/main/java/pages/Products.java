package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Products extends BasePage{
    By drpSort = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
    By lstProduct = By.xpath("//div[contains(@class,'inventory_list')]/div");
    By lstProduct_title = By.xpath(".//a[contains(@id,'title_link')]");
    By lstProduct_add = By.xpath(".//button[contains(@class,'btn_inventory')][contains(@id,'add-to-cart')]");
    By lstProduct_remove = By.xpath(".//button[contains(@class,'btn_inventory')][contains(@id,'remove')]");
    By btnCart = By.cssSelector(".shopping_cart_link");
    By listedProducts = By.cssSelector(".inventory_item_price");

    public Products(WebDriver driver) {
        super(driver);
    }

    private WebElement getProduct(String title) {
        return driver.findElements(lstProduct)
                .stream()
                .filter(element -> element.findElement(lstProduct_title).getText().equals(title))
                .findFirst()
                .orElseThrow();
    }

    public Products add(String title) {
        getProduct(title)
                .findElement(lstProduct_add)
                .click();
        return this;
    }

    public Products remove(String title) {
        getProduct(title)
                .findElement(lstProduct_remove)
                .click();
        return this;
    }

    public Products sort(String orderBy) {
        WebElement dropdown = driver.findElement(drpSort);
        Select select = new Select(dropdown);
        select.selectByVisibleText(orderBy);
        return this;
    }

    public Cart goToCart(){
        driver.findElement(btnCart).click();
        return new Cart(driver);
    }

    public List<WebElement> getListedProducts(){
        return driver.findElements(listedProducts);
    }
}
