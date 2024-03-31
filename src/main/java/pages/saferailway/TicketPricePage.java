package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketPricePage {

    private final WebDriver driver;

    public TicketPricePage(WebDriver driver) {
        this.driver = driver;
    }

    public BookTicketPage selectASeatType(String seatType) {
        driver.findElement(By.xpath(String.format("//td[text()='%s']/../td/a", seatType))).click();
        return new BookTicketPage(driver);
    }
}
