package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Basepage {
    protected WebDriver driver;

    public Basepage() {
        driver = WebdriverFactory.getDriver();
    }

    protected Basepage initElements(Object instance) {
        PageFactory.initElements(driver, instance);
        return this;
    }

    protected void enterText(WebElement element, String value)
    {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    protected void selectDropdown(WebElement dropdown, String selectValue, boolean isSelectionByValue) {
        Select select = new Select(dropdown);
        if(isSelectionByValue)
            select.selectByValue(selectValue);
        else
            select.selectByVisibleText(selectValue);
    }


    protected void selectDropdown(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        waitUntilElementIsDisplayed(element);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementIsDisplayed(WebElement element) {
        FluentWait<WebElement> wait = new FluentWait<>(element);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(webElement -> isElementDisplayed(webElement));
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/test-output/"+screenshotName+dateName+".png";
        System.out.println("destination = " + destination);
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
