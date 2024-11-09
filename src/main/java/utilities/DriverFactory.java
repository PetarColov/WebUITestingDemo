package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static WebDriver open(String browser) {

        WebDriver webDriver = null;

        switch (browser) {
            case "chrome":
                webDriver = WebDriverManager.chromedriver().avoidShutdownHook().create();
                break;
            case "firefox":
                webDriver = WebDriverManager.firefoxdriver().avoidShutdownHook().create();
                break;
        }
        return webDriver;
    }
}
