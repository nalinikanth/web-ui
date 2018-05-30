package pages;

import utils.Basepage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Generator;
import utils.PropertiesReader;

public class Registration extends Basepage{

    @FindBy(id = "id_gender2")
    protected WebElement radio_Mrs;

    @FindBy(id = "customer_firstname")
    private WebElement txt_firstName;

    @FindBy(id = "customer_lastname")
    private WebElement txt_LastName;

    @FindBy(id = "passwd")
    private WebElement txt_pwd;

    @FindBy(id = "days")
    private WebElement ddl_days;

    @FindBy(id = "months")
    private WebElement ddl_months;

    @FindBy(id = "years")
    private WebElement ddl_years;

    @FindBy(id = "company")
    private WebElement txt_company;

    @FindBy(id = "address1")
    private WebElement txt_address1;

    @FindBy(id = "address2")
    private WebElement txt_address2;

    @FindBy(id = "city")
    private WebElement txt_city;

    @FindBy(id = "id_state")
    private WebElement ddl_state;

    @FindBy(id = "postcode")
    private WebElement txt_postcode;

    @FindBy(id = "other")
    private WebElement txt_other;

    @FindBy(id = "phone")
    private WebElement txt_phone;

    @FindBy(id = "phone_mobile")
    private WebElement txt_mobile;

    @FindBy(id = "alias")
    private WebElement txt_alias;

    @FindBy(id = "submitAccount")
    private WebElement btn_submit;

    Registration() throws Exception {
        initElements(this);
    }


    public MyAccount registerForAccount() throws Exception {
        PropertiesReader.load(System.getProperty("user.dir") + "/env/default/account.properties");
        waitUntilElementIsClickable(radio_Mrs);
        radio_Mrs.click();
        enterText(txt_firstName,PropertiesReader.get("FirstName"));
        enterText(txt_LastName,PropertiesReader.get("LastName"));
        enterText(txt_pwd, Generator.randomText(10));
        selectDropdown(ddl_days,PropertiesReader.get("Day"), true);
        selectDropdown(ddl_months,PropertiesReader.get("Month"), true);
        selectDropdown(ddl_years,PropertiesReader.get("Year"), true);

        enterText(txt_company,Generator.randomShortText());
        enterText(txt_address1,Generator.randomShortText());
        enterText(txt_address2,Generator.randomShortText());
        enterText(txt_city,Generator.randomShortText());
        selectDropdown(ddl_state,PropertiesReader.get("State"), false);

        enterText(txt_postcode,Generator.randomInt(5));
        enterText(txt_other,Generator.randomShortText());
        enterText(txt_phone,Generator.randomInt(10));
        enterText(txt_mobile,Generator.randomInt(10));
        enterText(txt_alias,Generator.randomShortText());

        btn_submit.click();
        return new MyAccount();
    }


}
