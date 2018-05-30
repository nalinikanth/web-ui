package pages;

import utils.Basepage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends Basepage{

    @FindBy(className = "login")
    private WebElement signIn;

    @FindBy(className = "account")
    private WebElement userDetails;

    @FindBy(className = "logout")
    private WebElement logout;

    @FindBy(linkText = "Women")
    private WebElement lnk_women;

    public Header() throws Exception {
        super();
        initElements(this);
    }

    public Authentication initiateSignIn() throws Exception {
        waitUntilElementIsClickable(signIn);
        signIn.click();
        return new Authentication();
    }

    public String getUserdetails()
    {
        return userDetails.getText();
    }

    public boolean isLogoutDisplayed()
    {
        return isElementDisplayed(logout);
    }

    public CategoryPage gotoCategory(String categoryName) throws Exception {
        driver.findElement(By.linkText(categoryName)).click();
        return new CategoryPage();
    }
}
