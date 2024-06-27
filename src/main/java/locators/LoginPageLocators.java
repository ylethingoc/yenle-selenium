package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static By LOGIN_TEXT = By.xpath("//legend[text()='Log in to your account']");
    public static By USERNAME_TEXTBOX = By.cssSelector("#username");
    public static By PASSWORD_TEXTBOX = By.cssSelector("#password");
}
