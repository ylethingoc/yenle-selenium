import helpers.ToolsHelper;
import helpers.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.guerrillamail.EmailHomePage;
import pages.saferailway.MainPage;
import pages.saferailway.RegisterPage;

public class CreateAnAccountTest {

    EmailHomePage emailHomePage;
    MainPage mainPage;
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = WebDriverHelper.getWebDriver();
    }
    @Test(description = "Create an Safe Railway account")
    public void createAnRailwayAccount() {
        // navigate to https://www.guerrillamail.com/inbox and get an email
        driver.get("https://www.guerrillamail.com/inbox");
        String email = emailHomePage.getAnEmailAddress();
        ToolsHelper.writeKeyValue("account.properties", "usr", email);

        // navigate to http://saferailway.somee.com/
        WebDriverHelper.openTab(driver);
        driver.get("http://saferailway.somee.com/");
        mainPage.clickOnRegistryLabel();

        // create an account
        RegisterPage registerPage = mainPage.clickOnRegistryLabel();
        String passWord = registerPage.createANewAccount(email);
        ToolsHelper.writeKeyValue("account.properties", "pass", passWord);
        Assert.assertTrue(registerPage.getConfirmationMessage().contains("Thank you for registering your account"));

        // back to the first tab then check the confirmation email
        WebDriverHelper.closeTab(driver, true);
        WebDriverHelper.refreshPage(driver);
        emailHomePage.openTheConfirmationEmail(email);

        // click on the confirmation link and navigate back to the register page
        registerPage = emailHomePage.clickOnTheConfirmationLink();
        WebDriverHelper.switchToRightTab(driver);
        Assert.assertEquals("Registration Confirmed! You can now log in to the site.", registerPage.getConfirmationMessage());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
