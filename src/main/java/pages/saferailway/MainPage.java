package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    private final String dynamicLabel = "//span[text()='%s']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage clickOnRegistryLabel() {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Register"))).click();
        return new RegisterPage(driver);
    }

    public LoginPage clickOnLoginLabel(WebDriver driver) {
        driver.findElement(By.xpath(String.format(dynamicLabel, "Login"))).click();
        return new LoginPage(driver);
    }
}
