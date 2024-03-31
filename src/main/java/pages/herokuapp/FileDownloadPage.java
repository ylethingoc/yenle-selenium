package pages.herokuapp;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static helpers.Constants.WAIT_TIME_30_SECS;

public class FileDownloadPage {

    private final WebDriver driver;

    public FileDownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By pageTitle = By.cssSelector(".example>h3");
    private final By downloadableFileLabels = By.cssSelector(".example>a");

    public String getPageTitle() {
        return WebDriverHelper.waitForElement(driver, pageTitle).getText().trim();
    }

    public int getTotalDownloadableFiles() {
        List<WebElement> elements = driver.findElements(downloadableFileLabels);
        return elements.size();
    }

    public void downloadAFile(String fileName) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", fileName))).click();
    }

    public boolean isFileDownloaded(String fileName) {
        String filePath = String.format("/Users/yenle/Downloads/%s", fileName);
        File downloadedFile = new File(filePath);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_30_SECS);
        wait.until((WebDriver wd) -> downloadedFile.exists());
        return downloadedFile.exists();
    }
}
