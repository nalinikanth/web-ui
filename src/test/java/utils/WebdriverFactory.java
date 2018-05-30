package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.selenium.factory.WebDriverPool;

public class WebdriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";


    public static WebDriver getDriver() {
        String browserName = PropertiesReader.get("browser_name") == null ? CHROME :PropertiesReader.get("browser_name");

        if(browserName.equalsIgnoreCase(FIREFOX))
        {
            WebDriverManager.firefoxdriver().setup();
            return WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        }

        WebDriverManager.chromedriver().setup();
        return WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
    }


    public static void stopCurrentDriver(WebDriver driver)
    {
        WebDriverPool.DEFAULT.dismissDriver(driver);
    }
}
