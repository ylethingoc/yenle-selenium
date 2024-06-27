package com.saferailway.pages.saferailway;

import com.saferailway.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.saferailway.utils.Log;

import java.util.List;

import static com.saferailway.locators.BasePageLocators.DYNAMIC_LABEL;
import static com.saferailway.locators.BasePageLocators.SUBMIT_BUTTON;
import static com.saferailway.locators.BookTicketPageLocators.DEPART_STATION_OPTIONS;

public class BookTicketPage extends PageBase {

    public BookTicketPage(WebDriver driver) {
        super(driver);
    }

    public void bookTickets(int amount) {
        try {
            Log.info(String.format("Book %s ticket(s)", amount));
            List<WebElement> options = driver.findElements(DEPART_STATION_OPTIONS);
            for (int i = 1; i <= amount; i++) {
                Select select = new Select(driver.findElement(By.name("DepartStation")));
                int value;
                if (i <= options.size()) {
                    value = i;
                } else {
                    value = i - options.size();
                }
                select.selectByValue(String.valueOf(value));
                // to prevent page from corrupting
                Thread.sleep(getRandomSecond());
                scrollToElement(driver, SUBMIT_BUTTON).click();
                Thread.sleep(getRandomSecond());
                if (i != amount) {
                    driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "Book ticket"))).click();
                    Thread.sleep(getRandomSecond());
                }
            }
        } catch (InterruptedException e) {
            Log.error("An error occurred while booking tickets:\n", e);
            throw new RuntimeException(e);
        }
    }

    public MyTicketPage clickOnMyTicketLabel(WebDriver driver) {
        Log.info("Click on 'My ticket' label");
        driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "My ticket"))).click();
        return new MyTicketPage(driver);
    }
}
