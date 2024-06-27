package com.saferailway.pages.saferailway;

import com.saferailway.locators.BasePageLocators;
import com.saferailway.pages.PageBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.saferailway.utils.Log;

import java.util.ArrayList;
import java.util.List;

import static com.saferailway.locators.MyTicketLocators.CANCEL_BUTTON;
import static com.saferailway.locators.MyTicketLocators.DEPART_STATION_COLUMN;
import static com.saferailway.locators.MyTicketLocators.ERROR_MESSAGE;
import static com.saferailway.locators.MyTicketLocators.TABLES_ROWS;

public class MyTicketPage extends PageBase {

    public MyTicketPage(WebDriver driver) {
        super(driver);
    }

    public void pickAnOptionToFilter(String category, String option) {
        Log.info(String.format("Select option '%s' from '%s' category", option, category));
        scrollToElement(driver, BasePageLocators.SUBMIT_BUTTON);
        String name = "FilterDpStation";
        if (category.equalsIgnoreCase("status"))
            name = "FilterStatus";
        Select select = new Select(driver.findElement(By.name(name)));
        select.selectByVisibleText(option);
    }

    public void applyFilter() {
        Log.info("Click on 'Submit' button to apply filter");
        driver.findElement(BasePageLocators.SUBMIT_BUTTON).click();
        waitForPageLoaded(driver);
    }

    public List<String> getFilteredResult() {
        Log.info("Get filtered result");
        List<String> textList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(DEPART_STATION_COLUMN);
        for (WebElement element : elements) {
            String elementText = element.getText().trim();
            textList.add(elementText);
        }
        return textList;
    }

    public String getAnErrorMessage() {
        Log.info("Get an error message");
        return driver.findElement(ERROR_MESSAGE).getText().trim();
    }

    public void removeBookedTickets() {
        Log.info("Remove all tickets");
        pickAnOptionToFilter("Depart Station", "Ignore");
        applyFilter();

        try {
            List<WebElement> elements = driver.findElements(TABLES_ROWS);
            for (int i = 0; i < elements.size(); i++) {
                WebElement element = waitForElement(driver, CANCEL_BUTTON);
                element.click();
                Alert alert = waitForAlert(driver);
                alert.accept();
                waitForPageLoaded(driver);
            }
            Thread.sleep(getRandomSecond());
        } catch (InterruptedException e) {
            Log.error("An error occurred while removing tickets:\n", e);
            throw new RuntimeException(e);
        }
    }
}
