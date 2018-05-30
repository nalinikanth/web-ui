package com.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import utils.PropertiesReader;
import utils.WebdriverFactory;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.Home;
import pages.MyAccount;
import pages.ProductPage;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Listeners(TestListener.class)
public class WebTestFramework {

    public WebDriver driver;
    public WebDriverWait wait;
    static public Logger logger = Logger.getLogger(WebTestFramework.class);

    String existingUserEmail = "hf_challenge_123456@hf12345.com";
    String existingUserPassword = "12345678";

    @BeforeSuite
    public void setUpForTestSuite() {
        PropertiesReader.load(System.getProperty("user.dir") + "/env/default/browser.properties");
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/logs/log4j.properties");
    }

    @BeforeMethod
    public void setUpForTests() {
        logger.info("----------------Starting setUpForTests-----------");
        driver =  WebdriverFactory.getDriver();
        logger.info("Browser opened successfully");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10, 50);
        logger.debug("Browser window is maximized");
        System.out.println(PropertiesReader.get("homepage_url"));
        driver.get(PropertiesReader.get("homepage_url"));
        logger.info("----------------ending setUpForTests-----------");
    }

    @AfterMethod
    public void tearDown() {
        WebdriverFactory.stopCurrentDriver(driver);
    }

    @Test(priority = 1)
    public void registerANewUser_should_be_successful() throws Exception {
        logger.info("----------------Starting tests registerANewUser_should_be_successful-----------");
        Home homepage = new Home();
        MyAccount myAccount =  homepage.header.initiateSignIn().createAnAccount();
        myAccount.waitForPageToLoad();
        assertEquals(myAccount.getHeaderText(), "MY ACCOUNT");
        assertEquals(homepage.header.getUserdetails(),"FirstName LastName");
        assertTrue(myAccount.getWelcomeText().contains("Welcome to your account."));
        assertTrue(homepage.header.isLogoutDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        logger.info("----------------Ending tests registerANewUser_should_be_successful-----------");

    }

    @Test(priority = 2)
    public void signInWithExistingUser_should_be_successful() throws Exception {
        logger.info("----------------Starting tests signInWithExistingUser_should_be_successful-----------");
        logger.error("This should fail");
        String fullName = "Joe Black";
        Home homepage = new Home();
        MyAccount myAccount =  homepage.header.initiateSignIn().signIn(existingUserEmail,existingUserPassword);
        myAccount.waitForPageToLoad();

        assertEquals(myAccount.getHeaderText(), "MY ACCOUNT");
        assertEquals(homepage.header.getUserdetails(),fullName);
        assertTrue(myAccount.getWelcomeText().contains("Welcome to your account."));
        assertTrue(!homepage.header.isLogoutDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        logger.info("----------------Ending tests signInWithExistingUser_should_be_successful-----------");

    }

    @Test(priority = 3)
    public void checkoutTest() throws Exception {
        logger.info("----------------Starting tests checkoutTest-----------");
        Home homepage = new Home();
        MyAccount myAccount =  homepage.header.initiateSignIn().signIn(existingUserEmail,existingUserPassword);
        myAccount.waitForPageToLoad();
        ProductPage productPage = homepage.header.gotoCategory("Women").openProductByName("Faded Short Sleeve T-shirts");
        productPage.addToCartDefault().proceedToOrderPage();


        // Not converting further script into Page object model, As concept wise things may be clear now what I'm trying to achieve

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
        driver.findElement(By.name("processCarrier")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        assertEquals("ORDER CONFIRMATION", heading.getText());
        assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
        assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
        logger.info("----------------Ending tests checkoutTest-----------");
    }

}
