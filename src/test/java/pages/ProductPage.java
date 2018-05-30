package pages;

import utils.Basepage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends Basepage {

    LayerCart layercart = new LayerCart();
    @FindBy(name = "Submit")
    protected WebElement btn_addToCart;

    public ProductPage() throws Exception {
        initElements(this);
    }

    public LayerCart addToCartDefault()
    {
        btn_addToCart.click();
        return layercart;
    }
}
