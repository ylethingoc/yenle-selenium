package pages.saferailway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.PageBase;

import java.util.ArrayList;
import java.util.List;

public class MyTicketPage extends PageBase {

    private final By submitBtn = By.cssSelector("[type='submit']");
    private final By errorMessage = By.cssSelector(".error.message");
    private final By filteredDpStationResult = By.cssSelector(".MyTable>tbody>tr>td:nth-child(2)");
    private final By rowTables = By.cssSelector(".MyTable>tbody>:not(.TableSmallHeader)");
    private final By cancelBtn = By.cssSelector(".MyTable>tbody>tr:nth-child(2)>td>input[value='Cancel']");

    public MyTicketPage(WebDriver driver) {
        super(driver);
    }

    public void pickAnOptionToFilter(String category, String option) {
        scrollToElement(driver, submitBtn);
        String name = "FilterDpStation";
        if (category.equalsIgnoreCase("status"))
            name = "FilterStatus";
        Select select = new Select(driver.findElement(By.name(name)));
        select.selectByVisibleText(option);
    }

    public void applyFilter() {
        driver.findElement(submitBtn).click();
        waitForPageLoaded(driver);
    }

    public List<String> getFilteredResult() {
        List<String> textList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(filteredDpStationResult);
        for (WebElement element : elements) {
            String elementText = element.getText().trim();
            textList.add(elementText);
        }
        return textList;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText().trim();
    }

    public void removeBookedTickets() {
        pickAnOptionToFilter("Depart Station", "Ignore");
        applyFilter();

        try {
            List<WebElement> elements = driver.findElements(rowTables);
            for (int i = 0; i < elements.size(); i++) {
                WebElement element = waitForElement(driver, cancelBtn);
                element.click();
                Alert alert = waitForAlert(driver);
                alert.accept();
                waitForPageLoaded(driver);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
