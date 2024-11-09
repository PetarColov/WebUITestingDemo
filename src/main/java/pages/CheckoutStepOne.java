package pages;

import contexts.CheckoutInfoData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOne extends BasePage{
    By txtFName = By.id("first-name");
    By txtLName = By.id("last-name");
    By txtZip = By.id("postal-code");
    By btnContinue = By.id("continue");

    public CheckoutStepOne(WebDriver driver) {
        super(driver);
    }

    public CheckoutStepOne setInformation(CheckoutInfoData data) {
        driver.findElement(txtFName).sendKeys(data.getFirstName());
        driver.findElement(txtLName).sendKeys(data.getLastName());
        driver.findElement(txtZip).sendKeys(data.getZip());
        return new CheckoutStepOne(driver);
    }

    public CheckoutStepTwo continueBtn(){
        driver.findElement(btnContinue).click();
        return new CheckoutStepTwo(driver);
    }
}
