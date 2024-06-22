package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.PageBase;

import java.util.List;
import java.util.Random;

public class BookTicketPage extends PageBase {

    private final By submitBtn = By.cssSelector("[type='submit']");
    private final By departStationOptions = By.cssSelector("[name='DepartStation']>option");
    private final By bookTicketLabel = By.xpath("//span[text()='Book ticket']");
    private final By myTicketLabel = By.xpath("//span[text()='My ticket']");

    public BookTicketPage(WebDriver driver) {
        super(driver);
    }

    public void bookTickets(int amount) {
        try {
            PageBase.logger.info("bookTickets..start");
            List<WebElement> options = driver.findElements(departStationOptions);
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
                scrollToElement(driver, submitBtn).click();
                Thread.sleep(getRandomSecond());
                if (i != amount) {
                    driver.findElement(bookTicketLabel).click();
                    Thread.sleep(getRandomSecond());
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public MyTicketPage clickOnMyTicketLabel(WebDriver driver) {
        driver.findElement(myTicketLabel).click();
        return new MyTicketPage(driver);
    }

    private int getRandomSecond() {
        Random random = new Random();
        return (random.nextInt(3) + 1 ) * 1000;
    }
}
