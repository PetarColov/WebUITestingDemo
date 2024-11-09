package pages;

import enums.AppMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends BasePage{
    By btnMenu = By.id("react-burger-menu-btn");
    By navMenu = By.xpath("//nav[contains(@class, 'bm-item-list')]/a[contains(@class,'bm-item')]");
    By lblCart = By.id("shopping_cart_container");
    WebDriverWait wait;

    public Header(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public Header navigateToMenu(AppMenu menu) {
        driver.findElement(btnMenu).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement btnMenu = driver.findElements(navMenu)
                .stream()
                .filter(element -> element.getText().equalsIgnoreCase(menu.value()))
                .findFirst()
                .orElseThrow();
        wait.until(ExpectedConditions.elementToBeClickable(btnMenu)).click();
        return this;
    }

    public int getCartCount() {
        String count = driver.findElement(lblCart).getText();
        return count.isEmpty() ? 0: Integer.parseInt(count);
    }
}
