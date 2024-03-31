package pages.saferailway;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTicketPage {

    private final WebDriver driver;

    public BookTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void bookTickets(int dayFromNow, int amount) {
        selectADay(dayFromNow);
        selectAmount(String.valueOf(amount));
        driver.findElement(By.cssSelector("[type='submit']")).click();
    }

    public void selectADay(int dayFromNow) {
        LocalDate currentDay = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate next7Days = currentDay.plusDays(dayFromNow);
        Select select = new Select(driver.findElement(By.name("Date")));
        select.selectByVisibleText(formatter.format(next7Days));
    }

    public void selectAmount(String amount) {
        Select select = new Select(driver.findElement(By.name("TicketAmount")));
        select.selectByVisibleText(amount);
    }

    public String getSuccessMessage() {
        return driver.findElement(By.cssSelector("h1")).getText().trim();
    }
}
