package pages;

import utils.Basepage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends Basepage{

    @FindBy(css = "h1")
    protected WebElement heading;

    @FindBy(className = "info-account")
    protected WebElement account_welcome_msg;

    public MyAccount() throws Exception {
        initElements(this);
    }

    public String getHeaderText()
    {
        return heading.getText();
    }

    public String getWelcomeText()
    {
        return account_welcome_msg.getText();
    }

    public void waitForPageToLoad() {
        isElementDisplayed(heading);
    }
}
