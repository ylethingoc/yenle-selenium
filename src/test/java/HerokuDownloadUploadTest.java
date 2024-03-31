import helpers.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.herokuapp.FileDownloadPage;
import pages.herokuapp.FileUploadPage;
import pages.herokuapp.HerokuHomePage;

import static helpers.Constants.DOWNLOADED_FILE_NAME;
import static helpers.Constants.UPLOADED_FILE_NAME;

public class HerokuDownloadUploadTest {

    WebDriver driver;
    HerokuHomePage herokuHomePage;

    @BeforeClass
    public void setup() {
        driver = WebDriverHelper.getWebDriver();
        herokuHomePage = new HerokuHomePage(driver);
    }

    @Test(description = "Validate that user is able to download a file successfully", enabled = true)
    public void downloadAFile() {
        driver.get("https://the-internet.herokuapp.com/");
        FileDownloadPage fileDownloadPage = herokuHomePage.clickOnFileDownLoadLabel();
        Assert.assertEquals("File Downloader", fileDownloadPage.getPageTitle());
        Assert.assertTrue(fileDownloadPage.getTotalDownloadableFiles() > 0);

        fileDownloadPage.downloadAFile(DOWNLOADED_FILE_NAME);
        Assert.assertTrue(fileDownloadPage.isFileDownloaded(DOWNLOADED_FILE_NAME));
    }

    @Test(description = "Validate that user is able to upload a file successfully")
    public void uploadAFile() {
        driver.get("https://the-internet.herokuapp.com/");
        FileUploadPage fileUploadPage = herokuHomePage.clickOnFileUploadLabel();
        Assert.assertEquals("File Uploader", fileUploadPage.getPageTitle());

        fileUploadPage.uploadAFile(UPLOADED_FILE_NAME);
        Assert.assertEquals("File Uploaded!", fileUploadPage.getSuccessMessage());
        Assert.assertEquals(UPLOADED_FILE_NAME, fileUploadPage.getUploadedFileName());
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
