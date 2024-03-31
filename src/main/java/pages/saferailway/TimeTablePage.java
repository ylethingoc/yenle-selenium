package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimeTablePage {

    private final WebDriver driver;

    public TimeTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public TicketPricePage checkPriceForATrip(String origin, String destination) {
        String xpath = "//tr/td/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/../td/a[text()='check price']";
        driver.findElement(By.xpath(String.format(xpath, origin, destination))).click();
        return new TicketPricePage(driver);
    }
}
