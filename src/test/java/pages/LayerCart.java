package pages;

import utils.Basepage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LayerCart extends Basepage{

    @FindBy(xpath = "//*[@id='layer_cart']//a[@title='Proceed to checkout']")
    private WebElement btn_proceedToCheckout;

    public LayerCart() throws Exception {
        initElements(this);
    }

    public Orderpage proceedToOrderPage() throws Exception {
        waitUntilElementIsClickable(btn_proceedToCheckout);
        btn_proceedToCheckout.click();
        return new Orderpage();
    }
}
