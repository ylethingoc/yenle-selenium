package pages.herokuapp;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuHomePage {

    private final WebDriver driver;
    private final By fileDownloadLabel = By.xpath("//a[text()='File Download']");
    private final By fileUploadLabel = By.xpath("//a[text()='File Upload']");

    public HerokuHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public FileDownloadPage clickOnFileDownLoadLabel() {
        WebDriverHelper.waitForElement(driver, fileDownloadLabel).click();
        return new FileDownloadPage(driver);
    }

    public FileUploadPage clickOnFileUploadLabel() {
        WebDriverHelper.waitForElement(driver, fileUploadLabel).click();
        return new FileUploadPage(driver);
    }
}
