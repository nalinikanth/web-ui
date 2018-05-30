package pages;

import utils.Basepage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

public class Authentication extends Basepage{


    @FindBy(id = "email")
    private WebElement signInEmail;

    @FindBy(id = "passwd")
    private WebElement signInPwd;

    @FindBy(id = "SubmitLogin")
    private WebElement btn_signIn;

    @FindBy(id = "email_create")
    private WebElement txt_emailAddress;

    @FindBy(id = "SubmitCreate")
    private WebElement btn_createAnAccount;

    public Authentication() throws Exception {
        super();
        initElements(this);
    }
    public MyAccount createAnAccount() throws Exception {

        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";

        enterText(txt_emailAddress,email);
        Registration accountCreation = submit();
        return accountCreation.registerForAccount();

    }

    private Registration submit() throws Exception {
        btn_createAnAccount.click();
        return new Registration();
    }

    public MyAccount signIn(String existingUserEmail, String existingUserPassword) throws Exception {
        enterText(signInEmail,existingUserEmail);
        enterText(signInPwd,existingUserPassword);
        btn_signIn.click();
        return new MyAccount();
    }
}
