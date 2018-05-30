package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import utils.Basepage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends Basepage {

    public CategoryPage() throws Exception {
        initElements(this);
    }

    @FindBy(linkText = "More")
    private WebElement more;

    public ProductPage openProductByName(String productName) throws Exception {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(String.format("//a[@title='%s']/ancestor::li",productName)));
        waitUntilElementIsDisplayed(element);

        Point hoverItem =element.getLocation();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
        action.moveToElement(element).moveToElement(more).click(more).perform();

        return new ProductPage();
    }
}
