package pages.herokuapp;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage {
    WebDriver driver;

    private final By chooseFileButton = By.cssSelector("#file-upload");
    private final By submitButton = By.cssSelector("#file-submit");
    private final By successText = By.cssSelector(".example>h3");
    private final By uploadedFileText = By.cssSelector("#uploaded-files");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return WebDriverHelper.waitForElement(driver, By.cssSelector(".example>h3")).getText().trim();
    }

    public void selectAFile(String fileName) {
        String filePath = String.format("/Users/yenle/%s", fileName);
        WebDriverHelper.waitForElement(driver, chooseFileButton).sendKeys(filePath);
    }

    public void clickOnSubmitButton() {
        WebDriverHelper.waitForElement(driver, submitButton).click();
    }

    public void uploadAFile(String fileName) {
        selectAFile(fileName);
        clickOnSubmitButton();
    }

    public String getSuccessMessage() {
        return WebDriverHelper.waitForElement(driver, successText).getText();
    }

    public String getUploadedFileName() {
        return WebDriverHelper.waitForElement(driver, uploadedFileText).getText();
    }
}
