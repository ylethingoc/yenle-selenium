package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase;

public class HomePage extends PageBase {

    private final String dynamicLabel = "//span[text()='%s']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickOnLoginLabel(WebDriver driver) {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Login"))).click();
        return new LoginPage(driver);
    }

    public BookTicketPage clickBookTicketLabel(WebDriver driver) {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Book ticket"))).click();
        return new BookTicketPage(driver);
    }

    public RegisterPage clickOnRegisterLabel(WebDriver driver) {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Register"))).click();
        return new RegisterPage(driver);
    }

    public void clickOnLogOutLabel(WebDriver driver) {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Log out"))).click();
    }

}

